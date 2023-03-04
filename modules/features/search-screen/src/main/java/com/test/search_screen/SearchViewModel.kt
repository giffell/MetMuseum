package com.test.search_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.test.common.Result
import com.test.core_domain.usecases.GetSearchResultsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Provider

class SearchViewModel @Inject constructor(
    private val searchResultsUseCase: GetSearchResultsUseCase
) : ViewModel() {

    private val _searchUiState: MutableStateFlow<SearchUiState> = MutableStateFlow(SearchUiState.EmptyResults)
    val searchUiState = _searchUiState.asStateFlow()

    fun loadSearch(searchQuery: String) {
        searchResultsUseCase(GetSearchResultsUseCase.Params(searchQuery))
            .onEach {
                val uiState = when (it) {
                    is Result.Success -> {
                        if (it.data.objectIDs.isNotEmpty()) {
                            SearchUiState.Success(it.data)
                        } else {
                            SearchUiState.EmptyResults
                        }
                    }
                    is Result.Error -> SearchUiState.Error(it.exception)
                    is Result.Loading -> SearchUiState.Loading(it.showProgress)
                }
                _searchUiState.emit(uiState)
            }
            .onCompletion { _searchUiState.emit(SearchUiState.Loading(false)) }
            .launchIn(viewModelScope)
    }

    class Factory @Inject constructor(
        private val searchResultsUseCase: Provider<GetSearchResultsUseCase>
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == SearchViewModel::class.java)
            return SearchViewModel(searchResultsUseCase.get()) as T
        }
    }
}
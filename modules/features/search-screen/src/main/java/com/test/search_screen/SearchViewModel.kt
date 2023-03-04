package com.test.search_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.core_domain.usecases.GetSearchResultsUseCase
import javax.inject.Inject
import javax.inject.Provider

class SearchViewModel @Inject constructor(
    private val searchResultsUseCase: GetSearchResultsUseCase
) : ViewModel() {

    fun loadSearch(searchQuery: String) {
        // TODO
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
package com.test.object_details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.test.common.Result
import com.test.core_domain.usecases.GetObjectDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Provider

class ObjectDetailsViewModel(
    private val getObjectDetailsUseCase: GetObjectDetailsUseCase
) : ViewModel() {

    private val _objectDetailsUiState: MutableStateFlow<ObjectDetailsUiState> = MutableStateFlow(ObjectDetailsUiState.Loading(true))
    val objectDetailsUiState = _objectDetailsUiState.asStateFlow()

    fun objectDetailsInfo(objectId: Int) {
        getObjectDetailsUseCase(GetObjectDetailsUseCase.Params(objectId))
            .onEach {
                val uiState = when (it) {
                    is Result.Success -> ObjectDetailsUiState.Success(it.data)
                    is Result.Error -> ObjectDetailsUiState.Error(it.exception)
                    is Result.Loading -> ObjectDetailsUiState.Loading(it.showProgress)
                }
                _objectDetailsUiState.emit(uiState)
            }
            .onCompletion { _objectDetailsUiState.emit(ObjectDetailsUiState.Loading(false)) }
            .launchIn(viewModelScope)
    }

    class Factory @Inject constructor(
        private val getObjectDetailsUseCase: Provider<GetObjectDetailsUseCase>
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == ObjectDetailsViewModel::class.java)
            return ObjectDetailsViewModel(getObjectDetailsUseCase.get()) as T
        }
    }
}
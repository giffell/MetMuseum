package com.test.object_details_screen

import com.test.core_model.ObjectDetailsInfo

sealed interface ObjectDetailsUiState {
    data class Success(val objectDetailsInfo: ObjectDetailsInfo) : ObjectDetailsUiState
    data class Loading(val showProgress: Boolean) : ObjectDetailsUiState
    data class Error(val exception: Throwable) : ObjectDetailsUiState
}
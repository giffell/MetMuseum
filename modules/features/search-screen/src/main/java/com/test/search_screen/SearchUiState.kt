package com.test.search_screen

import com.test.core_model.SearchResult

sealed interface SearchUiState {
    data class Success(val searchResult: SearchResult) : SearchUiState
    object EmptyResults : SearchUiState
    data class Loading(val showProgress: Boolean) : SearchUiState
    data class Error(val exception: Throwable) : SearchUiState
}
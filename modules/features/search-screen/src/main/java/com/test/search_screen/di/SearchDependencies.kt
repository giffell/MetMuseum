package com.test.search_screen.di

import com.test.core_domain.usecases.GetSearchResultsUseCase
import com.test.search_screen.SearchRouter

interface SearchDependencies {
    fun getClientsListRouter(): SearchRouter
    fun getSearchResultsUseCase(): GetSearchResultsUseCase
}
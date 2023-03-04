package com.test.metmuseum.di

import com.test.metmuseum.navigation.Router
import com.test.navigation.BaseNavigator
import com.test.search_screen.SearchRouter
import dagger.Module
import dagger.Provides

@Module
object NavigationModule {

    @Provides
    fun provideBaseNavigator(router: Router): BaseNavigator = router

    @Provides
    fun provideSearchRouter(router: Router): SearchRouter = router
}
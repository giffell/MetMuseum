package com.test.metmuseum.di

import com.test.metmuseum.navigation.Router
import com.test.navigation.BaseNavigator
import dagger.Module
import dagger.Provides

@Module
object NavigationModule {

    @Provides
    fun provideBaseNavigator(router: Router): BaseNavigator = router
}
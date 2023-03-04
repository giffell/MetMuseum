package com.test.metmuseum.di

import com.test.common.network.ActivityScope
import com.test.metmuseum.MainActivity
import com.test.search_screen.di.SearchDependencies
import dagger.Subcomponent

@ActivityScope
@Subcomponent(
    modules = [
        NavigationModule::class
    ]
)
interface ActivityComponent : SearchDependencies {
    fun inject(activity: MainActivity)
}
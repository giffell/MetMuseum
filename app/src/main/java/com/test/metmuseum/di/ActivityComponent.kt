package com.test.metmuseum.di

import com.test.common.network.ActivityScope
import com.test.metmuseum.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(
    modules = [
        NavigationModule::class
    ]
)
interface ActivityComponent {
    fun inject(activity: MainActivity)
}
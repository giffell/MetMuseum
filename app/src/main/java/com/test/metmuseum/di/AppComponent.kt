package com.test.metmuseum.di

import com.test.core_data.di.ApiModule
import com.test.core_network.di.NetworkModule
import com.test.metmuseum.AppApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        ApiModule::class
    ]
)
interface AppComponent {

    fun inject(app: AppApplication)
}
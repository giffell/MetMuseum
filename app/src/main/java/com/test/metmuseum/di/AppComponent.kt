package com.test.metmuseum.di

import com.test.common.network.di.DispatchersModule
import com.test.core_data.di.RepositoryModule
import com.test.core_network.di.ApiModule
import com.test.core_network.di.NetworkModule
import com.test.metmuseum.AppApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        ApiModule::class,
        DispatchersModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {

    fun inject(app: AppApplication)
}
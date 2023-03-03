package com.test.common.network.di

import com.test.common.network.AppCoroutineDispatchers
import com.test.common.network.Dispatcher
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
object DispatchersModule {

    @Provides
    @Dispatcher(AppCoroutineDispatchers.IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Dispatcher(AppCoroutineDispatchers.COMPUTATION)
    fun providesComputationDispatcher(): CoroutineDispatcher = Dispatchers.Default
}
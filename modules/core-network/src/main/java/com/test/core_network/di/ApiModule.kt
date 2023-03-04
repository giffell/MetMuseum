package com.test.core_network.di

import com.test.core_network.api.MetMuseumDataSource
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object ApiModule {

    @Singleton
    @Provides
    fun provideRemoteApiDataSource(
        retrofit: Retrofit
    ): MetMuseumDataSource {
        return retrofit.create(MetMuseumDataSource::class.java)
    }
}
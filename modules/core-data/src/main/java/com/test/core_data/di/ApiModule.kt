package com.test.core_data.di

import com.test.core_data.api.MetMuseumDataSource
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
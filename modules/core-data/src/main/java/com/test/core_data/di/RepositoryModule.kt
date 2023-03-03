package com.test.core_data.di

import com.test.core_data.repositories.MetMuseumRepositoryImpl
import com.test.core_domain.repositories.MetMuseumRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideMetMuseumRepository(metMuseumRepositoryImpl: MetMuseumRepositoryImpl): MetMuseumRepository
}
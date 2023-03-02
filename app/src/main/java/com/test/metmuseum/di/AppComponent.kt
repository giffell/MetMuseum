package com.test.metmuseum.di

import com.test.metmuseum.AppApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface AppComponent {

    fun inject(app: AppApplication)
}
package com.test.metmuseum

import android.app.Application
import com.test.metmuseum.di.AppComponent
import com.test.metmuseum.di.DaggerAppComponent
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager

class AppApplication : Application(), IHasComponent<AppComponent> {

    override fun onCreate() {
        super.onCreate()
        XInjectionManager.bindComponent(this).inject(this)
    }

    override fun getComponent(): AppComponent = DaggerAppComponent.builder().build()
}
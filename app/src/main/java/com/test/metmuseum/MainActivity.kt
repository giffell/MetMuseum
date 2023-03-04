package com.test.metmuseum

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.metmuseum.di.ActivityComponent
import com.test.metmuseum.di.AppComponent
import com.test.navigation.BaseNavigator
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main), IHasComponent<ActivityComponent> {

    @Inject
    lateinit var router: BaseNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        XInjectionManager.bindComponent(this).inject(this)
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(router)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(router)
    }

    override fun getComponent(): ActivityComponent = XInjectionManager.findComponent<AppComponent>().init()
}
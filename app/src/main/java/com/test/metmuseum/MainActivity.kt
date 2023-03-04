package com.test.metmuseum

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.metmuseum.di.ActivityComponent
import com.test.metmuseum.di.AppComponent
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager

class MainActivity : AppCompatActivity(R.layout.activity_main), IHasComponent<ActivityComponent> {

    override fun onCreate(savedInstanceState: Bundle?) {
        XInjectionManager.bindComponent(this).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun getComponent(): ActivityComponent = XInjectionManager.findComponent<AppComponent>().init()
}
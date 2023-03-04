package com.test.navigation

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.navigation.NavController

abstract class BaseNavigator : DefaultLifecycleObserver {
    protected var navController: NavController? = null

    abstract fun navigateBack()
}
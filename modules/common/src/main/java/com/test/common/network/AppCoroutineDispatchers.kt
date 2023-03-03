package com.test.common.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val appCoroutineDispatchers: AppCoroutineDispatchers)

enum class AppCoroutineDispatchers {
    IO,
    COMPUTATION
}

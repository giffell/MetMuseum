package com.test.buildsrc

object Deps {

    object Android {
        private const val ktxVersion = "1.9.0"
        const val ktx = "androidx.core:core-ktx:$ktxVersion"

        private const val appCompatVersion = "1.6.1"
        const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"

        private const val materialVersion = "1.8.0"
        const val material = "com.google.android.material:material:$materialVersion"

        private const val startUpVersion = "1.1.1"
        const val startup = "androidx.startup:startup-runtime:$startUpVersion"

        const val coroutinesVersion = "1.6.4"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"

        const val navigationVersion = "2.5.3"
        const val navigation = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"

        private const val lifecycleViewModelKtxVersion = "2.5.1"
        const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleViewModelKtxVersion"

        private const val fragmentKtxVersion = "1.5.5"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:$fragmentKtxVersion"
    }

    object Libraries {
        private const val timberVersion = "5.0.1"
        const val timber = "com.jakewharton.timber:timber:$timberVersion"

        private const val sandwichVersion = "1.3.2"
        const val sandwich = "com.github.skydoves:sandwich:$sandwichVersion"

        private const val viewBindingVersion = "1.5.3"
        const val viewBinding = "com.github.kirich1409:viewbindingpropertydelegate-noreflection:$viewBindingVersion"

        private const val adapterDelegatesVersion = "4.3.2"
        const val adapterDelegates = "com.hannesdorfmann:adapterdelegates4-kotlin-dsl-viewbinding:$adapterDelegatesVersion"

        private const val coilVersion = "2.2.2"
        const val coil = "io.coil-kt:coil:$coilVersion"
    }

    object Retrofit {
        private const val retrofitVersion = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"
        private const val logginInterceptorVersion = "4.10.0"
        const val logginInterceptor = "com.squareup.okhttp3:logging-interceptor:$logginInterceptorVersion"

        private const val moshiVersion = "1.14.0"
        const val moshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion"
        const val moshi = "com.squareup.moshi:moshi-kotlin:$moshiVersion"
    }

    object Dagger {
        private const val daggerVersion = "2.45"
        const val dagger = "com.google.dagger:dagger:$daggerVersion"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"

        private const val componentManagerVersion = "2.1.3"
        const val componentManager = "com.github.valeryponomarenko.componentsmanager:androidx:$componentManagerVersion"
    }

    object Testing {
        private const val junitVersion = "4.13.2"
        const val junit = "junit:junit:$junitVersion"

        private const val junitExtVersion = "1.1.5"
        const val junitExt = "androidx.test.ext:junit:$junitExtVersion"

        private const val mockitoVersion = "2.2.0"
        const val mockito = "com.nhaarman.mockitokotlin2:mockito-kotlin:$mockitoVersion"
        private const val mockitoInlineVersion = "5.1.1"
        const val mockitoInline = "org.mockito:mockito-inline:$mockitoInlineVersion"

        private const val turbineVersion = "0.12.1"
        const val turbine = "app.cash.turbine:turbine:$turbineVersion"

        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Android.coroutinesVersion}"

        private const val kaspressoVersion = "1.5.1"
        const val kaspresso = "com.kaspersky.android-components:kaspresso:$kaspressoVersion"
    }
}
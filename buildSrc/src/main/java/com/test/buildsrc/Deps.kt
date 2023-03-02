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
    }

    object Libraries {
        private const val timberVersion = "5.0.1"
        const val timber = "com.jakewharton.timber:timber:$timberVersion"
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
    }
}
package com.test.buildsrc

object Deps {

    object Android {
        private const val ktxVersion = "1.9.0"
        const val ktx = "androidx.core:core-ktx:$ktxVersion"

        private const val appCompatVersion = "1.6.1"
        const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"

        private const val materialVersion = "1.8.0"
        const val material = "com.google.android.material:material:$materialVersion"
    }

    object Testing {
        private const val junitVersion = "4.13.2"
        const val junit = "junit:junit:$junitVersion"

        private const val junitExtVersion = "1.1.5"
        const val junitExt = "androidx.test.ext:junit:$junitExtVersion"
    }
}
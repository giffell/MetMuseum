import com.test.buildsrc.App

plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.test.core_model"

    compileSdk = App.compileSdkVersion

    defaultConfig {

        minSdk = App.midSdkVersion
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

}

dependencies {

}
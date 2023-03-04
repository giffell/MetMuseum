import com.test.buildsrc.App
import com.test.buildsrc.Deps

plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.test.search_screen"

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
    implementation(project(":modules:navigation"))

    implementation(Deps.Android.ktx)
    implementation(Deps.Android.appCompat)
    implementation(Deps.Android.material)
}
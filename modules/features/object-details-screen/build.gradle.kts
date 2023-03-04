import com.test.buildsrc.App
import com.test.buildsrc.Deps

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.test.object_details_screen"

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

    buildFeatures {
        viewBinding = true
    }

}

dependencies {
    implementation(project(":modules:common"))
    api(project(":modules:core-domain"))
    api(project(":modules:core-model"))

    implementation(Deps.Android.ktx)
    implementation(Deps.Android.appCompat)
    implementation(Deps.Android.material)
    implementation(Deps.Android.navigation)

    implementation(Deps.Dagger.dagger)
    implementation(Deps.Dagger.componentManager)
    kapt(Deps.Dagger.daggerCompiler)

    implementation(Deps.Libraries.adapterDelegates)
    implementation(Deps.Libraries.viewBinding)
    implementation(Deps.Libraries.timber)
}
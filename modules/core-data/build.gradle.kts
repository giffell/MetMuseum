import com.test.buildsrc.App
import com.test.buildsrc.Deps

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.test.core_data"

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
    api(project(":modules:core-domain"))
    implementation(project(":modules:common"))

    implementation(Deps.Dagger.dagger)
    kapt(Deps.Dagger.daggerCompiler)

    implementation(Deps.Android.coroutines)

    implementation(Deps.Retrofit.retrofit)
    implementation(Deps.Retrofit.moshi)
    kapt(Deps.Retrofit.moshiCodeGen)

    implementation(Deps.Libraries.sandwich)
}
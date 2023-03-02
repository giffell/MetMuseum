import com.test.buildsrc.App
import com.test.buildsrc.Deps

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.test.core_network"

    compileSdk = App.compileSdkVersion

    defaultConfig {
        buildConfigField("String", "BASE_URL", "\"${"https://collectionapi.metmuseum.org/public/collection/v1/"}\"")

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
    implementation(Deps.Dagger.dagger)
    kapt(Deps.Dagger.daggerCompiler)

    implementation(Deps.Android.coroutines)

    api(Deps.Retrofit.retrofit)
    api(Deps.Retrofit.logginInterceptor)
    implementation(Deps.Retrofit.moshiConverter)
    implementation(Deps.Libraries.sandwich)

}
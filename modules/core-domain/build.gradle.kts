import com.test.buildsrc.App
import com.test.buildsrc.Deps

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.test.core_domain"

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
    implementation(project(":modules:common"))
    implementation(project(":modules:core-model"))

    implementation(Deps.Dagger.dagger)
    kapt(Deps.Dagger.daggerCompiler)

    implementation(Deps.Android.coroutines)

    testImplementation(project(":modules:core-test"))
    testImplementation(Deps.Testing.junit)
    testImplementation(Deps.Testing.coroutinesTest)
    testImplementation(Deps.Testing.turbine)
    testImplementation(Deps.Testing.mockito)
    testImplementation(Deps.Testing.mockitoInline)
    testImplementation(Deps.Android.coroutines)
}
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
    implementation(project(":modules:core-domain"))
    implementation(project(":modules:core-network"))
    implementation(project(":modules:common"))

    implementation(Deps.Dagger.dagger)
    kapt(Deps.Dagger.daggerCompiler)

    implementation(Deps.Android.coroutines)

    implementation(Deps.Libraries.sandwich)
    implementation(Deps.Libraries.timber)

    testImplementation(project(":modules:core-test"))
    testImplementation(Deps.Testing.junit)
    testImplementation(Deps.Testing.coroutinesTest)
    testImplementation(Deps.Testing.turbine)
    testImplementation(Deps.Testing.mockito)
    testImplementation(Deps.Testing.mockitoInline)
    testImplementation(Deps.Android.coroutines)
}
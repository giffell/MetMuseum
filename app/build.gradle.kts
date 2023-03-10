import com.test.buildsrc.App
import com.test.buildsrc.Deps

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.test.metmuseum"
    compileSdk = App.compileSdkVersion

    defaultConfig {
        applicationId = App.applicationId
        minSdk = App.midSdkVersion
        targetSdk = App.targetSdkVersion
        versionCode = App.versionCode
        versionName = App.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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

    implementation(project(":modules:core-network"))
    implementation(project(":modules:core-data"))
    implementation(project(":modules:core-domain"))
    implementation(project(":modules:common"))
    implementation(project(":modules:navigation"))
    implementation(project(":modules:features:search-screen"))
    implementation(project(":modules:features:object-details-screen"))

    implementation(Deps.Android.ktx)
    implementation(Deps.Android.appCompat)
    implementation(Deps.Android.material)
    implementation(Deps.Android.startup)
    implementation(Deps.Android.navigation)

    implementation(Deps.Libraries.timber)

    implementation(Deps.Dagger.dagger)
    implementation(Deps.Dagger.componentManager)
    kapt(Deps.Dagger.daggerCompiler)

    androidTestImplementation(Deps.Testing.junitExt)
    androidTestImplementation(Deps.Testing.kaspresso)
}
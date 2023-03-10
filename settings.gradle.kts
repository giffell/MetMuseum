pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}
rootProject.name = "MetMuseum"
include(":app")
include(":modules:core-network")
include(":modules:core-data")
include(":modules:core-domain")
include(":modules:common")
include(":modules:core-model")
include(":modules:navigation")
include(":modules:features:search-screen")
include(":modules:features:object-details-screen")
include(":modules:core-test")

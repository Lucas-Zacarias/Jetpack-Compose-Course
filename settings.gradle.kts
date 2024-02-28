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
    }
}

rootProject.name = "Jetpack Compose Course"
include(":app")
include(":kotlin")
include(":composebasics")
include(":lemon")
include(":diceroller")
include(":tipcalculator")
include(":artspace")
include(":affirmations")
include(":courses")
include(":woof")
include(":superheroes")
include(":30days")
include(":unscramble")

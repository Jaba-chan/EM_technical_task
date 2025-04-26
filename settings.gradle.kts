pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "EM_technical_task"
include(":app")
include(":core")
include(":core:data")
include(":core:domain")
include(":feature")
include(":feature:courses")
include(":feature:favorite")
include(":feature:onboarding")
include(":feature:login")
include(":core:model")
include(":core:network")
include(":core:database")
include(":core:common")
include(":core:resources")
include(":core:resources")
include(":core:ui")

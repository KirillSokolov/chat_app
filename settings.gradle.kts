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
        maven { url = uri("https://www.jitpack.io" ) }
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://www.jitpack.io" ) }

    }
}

rootProject.name = "Test work"
include(":app")
include(":core:navigation")
include(":core:ui")
include(":data")
include(":data_api")
include(":domain_models")
include(":features:chat_list")
include(":features:chat_list_api")
include(":features:chat")
include(":features:chat_api")
include(":features:authorization")
include(":features:authorization_api")
include(":features:user_details")
include(":features:user_details_api")

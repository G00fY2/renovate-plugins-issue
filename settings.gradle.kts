pluginManagement {
    repositories {
        google {
            content {
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
                includeGroupAndSubgroups("androidx")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://developer.huawei.com/repo/")
            content {
                includeGroupAndSubgroups("com.huawei")
            }
        }
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "RenvoateIssue"

includeBuild("build-logic")
include(":app")

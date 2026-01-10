rootProject.name = "build-logic"

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        google {
            content {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        maven {
            url = uri("https://developer.huawei.com/repo/")
            content {
                includeGroupAndSubgroups("com.huawei")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

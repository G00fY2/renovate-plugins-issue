rootProject.name = "renovate-plugins-issue"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
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
    }
}

pluginManagement {
    repositories {
        google {
            @Suppress("UnstableApiUsage")
            content {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        maven {
            url = uri("https://developer.huawei.com/repo/")
            @Suppress("UnstableApiUsage")
            content {
                includeGroupAndSubgroups("com.huawei")
            }
        }
        gradlePluginPortal()
        mavenCentral()
    }

    plugins {
        id("com.android.settings") version extractVersionFromCatalog("androidPlugin")
    }
}

plugins {
    id("com.android.settings")
}

settings.extensions.configure<com.android.build.api.dsl.SettingsExtension> {
    execution {
        profiles {
            create("default") {
                r8 {
                    jvmOptions += emptyList()
                    runInSeparateProcess = false
                }
            }
            create("ci") {
                r8 {
                    jvmOptions += "-Xms3g -Xmx6g -XX:+HeapDumpOnOutOfMemoryError -Dfile.encoding=UTF-8 -XX:+UseParallelGC -XX:SoftRefLRUPolicyMSPerMB=1".split(" ")
                    runInSeparateProcess = true
                }
            }
            defaultProfile = "default"
        }
    }
}

includeBuild("build-logic")
include(":app")

private fun extractVersionFromCatalog(key: String): String {
    return file("$rootDir/gradle/libs.versions.toml").useLines { lines ->
        lines.first { it.contains(key) }
            .substringAfter("=")
            .trim()
            .removeSurrounding("\"")
    }
}

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.realm)
    alias(libs.plugins.kmp.nativecoroutines)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.skie)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_11.toString()
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "Shared"
        }
    }

    sourceSets {
        all {
            languageSettings.optIn("kotlinx.cinterop.ExperimentalForeignApi")
            languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
        }

        commonMain.dependencies {
            implementation(libs.realm)
            implementation(libs.koin.core)
            implementation(libs.multiplatform.settings)

            implementation(libs.ktor.core)
            implementation(libs.ktor.json.serialization)
            implementation(libs.ktor.content.negotiation)
            implementation(libs.ktor.logging)

            implementation(libs.kotlinx.serialization)
            implementation(libs.kotlinx.coroutine)
            implementation(libs.kotlinx.datetime)

            api(libs.kermit.logger)
            api(libs.kmm.viewmodel)
            implementation(libs.kotlin.yaml)
            implementation(kotlin("stdlib-common"))
        }

        androidMain.dependencies {
            implementation(libs.ktor.okhttp)
            implementation(libs.ktor.android)
            implementation(libs.koin.android)
        }

        iosMain.dependencies {
            implementation(libs.ktor.darwin)
            implementation(libs.ktor.ios)
        }
    }
}

android {
    val applicationId: String by project
    val compileSdkVersion: String by project
    val minSdkVersion: String by project

    namespace = applicationId
    compileSdk = compileSdkVersion.toInt()
    defaultConfig {
        minSdk = minSdkVersion.toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

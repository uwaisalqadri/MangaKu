plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kmp.nativecoroutines)
    alias(libs.plugins.kotlin.serialization)
//    alias(libs.plugins.sqlDelight)
    alias(libs.plugins.skie)
    alias(libs.plugins.room)
}

android {
    val applicationId: String by project
    val compileSdkVersion: String by project
    val minSdkVersion: String by project

    namespace = applicationId
    compileSdk = compileSdkVersion.toInt()
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = minSdkVersion.toInt()
    }
}

kotlin {
    jvmToolchain(17)

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "Shared"
        }
    }

    androidTarget()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)
            implementation(libs.multiplatform.settings)

            implementation(libs.ktor.core)
            implementation(libs.ktor.json.serialization)
            implementation(libs.ktor.content.negotiation)
            implementation(libs.ktor.logging)

            implementation(libs.sqldelight.runtime)
            implementation(libs.sqldelight.coroutines.extensions)

            implementation(libs.kotlinx.serialization)
            implementation(libs.kotlinx.coroutine)
            implementation(libs.kotlinx.datetime)

            api(libs.kermit.logger)
            api(libs.kmp.viewModel)
            api(libs.androidx.room.runtime)
            api(libs.androidx.sqlite.bundled)
            api(libs.androidx.sqlite.framework)
            api(libs.androidx.sqlite)
            implementation(libs.kotlin.yaml)
            implementation(kotlin("stdlib-common"))
        }

        androidMain.dependencies {
            implementation(libs.ktor.okhttp)
            implementation(libs.ktor.android)
            implementation(libs.koin.android)
            implementation(libs.sqldelight.android.driver)
        }

        iosMain.dependencies {
            implementation(libs.ktor.darwin)
            implementation(libs.ktor.ios)
            implementation(libs.sqldelight.native.driver)
        }
    }
}

dependencies {
    ksp(libs.androidx.room.ksp)
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlinx.cinterop.ExperimentalForeignApi")
    languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
}

room {
    schemaDirectory("$projectDir/schemas")
}

skie {
    features {
        enableSwiftUIObservingPreview = true
    }
}
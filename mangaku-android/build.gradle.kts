@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp") version libs.versions.ksp.get()
}

android {
    val applicationId: String by project
    val compileSdkVersion: String by project
    val minSdkVersion: String by project
    val targetSdkVersion: String by project

    namespace = applicationId
    compileSdk = compileSdkVersion.toInt()

    defaultConfig {
        this.applicationId = applicationId
        minSdk = minSdkVersion.toInt()
        targetSdk = targetSdkVersion.toInt()

        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
        viewBinding = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()

        freeCompilerArgs += listOf(
            "-P",
            "plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=true"
        )
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }


    applicationVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
    }
}

dependencies {

    implementation(project(":shared"))
    implementation(libs.android.material)
    implementation(libs.android.app.compat)
    implementation(libs.constraint.layout)
    implementation(libs.fragment.navigation)
    implementation(libs.android.navigation)
    implementation(libs.kotlinx.livedata)

    implementation(libs.compose.ui)
    implementation(libs.compose.material)
    implementation(libs.compose.tooling)
    implementation(libs.compose.foundation)
    implementation(libs.compose.foundation.layout)
    implementation(libs.compose.graphics)
    implementation(libs.compose.activity)
    implementation(libs.compose.material.icon)
    implementation(libs.compose.util)
    implementation(libs.compose.lottie)
    implementation(libs.compose.shimmer)
    implementation(libs.compose.livedata)

    implementation(libs.koin.android)
    implementation(libs.koin.compose)

    implementation(libs.accompanist.coil)
    implementation(libs.accompanist.pager)

    implementation(libs.compose.destinations)
    implementation(libs.compose.destinations.animation)
    ksp(libs.compose.destinations.ksp)
}
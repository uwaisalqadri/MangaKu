plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("kotlin-android")
    id("com.google.devtools.ksp") version "${Versions.kotlin}-1.0.5"
}

android {
    compileSdk = AndroidConfigs.compileSdkVersion

    defaultConfig {
        applicationId = AndroidConfigs.applicationId
        minSdk = AndroidConfigs.minSdkVersion
        targetSdk = AndroidConfigs.targetSdkVersion
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
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }

    kotlinOptions {
        jvmTarget = "1.8"
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
    with(Dependencies) {
        implementation(androidMaterial)
        implementation(androidAppCompat)
        implementation(constraintLayout)
        implementation(fragmentNavigation)
        implementation(androidNavigation)
        implementation(liveDataKtx)

        implementation(composeUi)
        implementation(composeMaterial)
        implementation(composeTooling)
        implementation(composeFoundation)
        implementation(composeFoundationLayout)
        implementation(composeGraphics)
        implementation(composeActivity)
        implementation(composeMaterialIcon)
        implementation(composeUtil)
        implementation(composeLottie)
        implementation(composeShimmer)
        implementation(composeLiveData)

        implementation(koinAndroid)
        implementation(koinCompose)

        implementation(accompanistCoil)
        implementation(accompanistPager)

        implementation(composeDestinations)
        implementation(composeDestinationsAnimation)
        ksp(composeDestinationsKsp)
    }
}
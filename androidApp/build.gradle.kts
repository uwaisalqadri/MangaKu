plugins {
    id("com.android.application")
    kotlin("android")
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

    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"

    }

    composeOptions {
        kotlinCompilerExtensionVersion = AndroidConfigs.kotlinCompilerExtensionVersion
    }

    packagingOptions {
        resources.excludes.apply {
            add("META-INF/AL2.0")
            add("META-INF/LGPL2.1")
        }
    }
}

dependencies {
    implementation(project(":core"))

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
        implementation(composeNavigation)
        implementation(composeMaterialIcon)
        implementation(composeUtil)
        implementation(composeLottie)
        implementation(composeShimmer)

        implementation(ktorAndroid)
        implementation(koinAndroid)
        implementation(koinCompose)

        implementation(accompanistCoil)
        implementation(accompanistPager)
    }
}
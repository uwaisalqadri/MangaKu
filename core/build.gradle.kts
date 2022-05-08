plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization") version Versions.kotlin
    id("com.android.library")
    id("io.realm.kotlin") version Versions.realm
    id("com.rickclephas.kmp.nativecoroutines") version "0.11.3"
    id("koin")
    id("dev.icerock.moko.kswift") version "0.5.0"
}

// CocoaPods requires the podspec to have a version.
version = "1.5"

android {
    compileSdk = AndroidConfigs.compileSdkVersion
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        minSdk = AndroidConfigs.minSdkVersion
        targetSdk = AndroidConfigs.targetSdkVersion
    }
}

kotlin {
    android()
    iosX64()
    iosArm64()
    //iosSimulatorArm64() sure all ios dependencies support this target

    cocoapods {
        summary = "MangaKu"
        homepage = "https://github.com/uwaisalqadri/MangaKu"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "KotlinCore"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                with(Dependencies) {
                    implementation(realmKotlin)
                    implementation(koinCore)

                    implementation(ktorCore)
                    implementation(ktorJsonSerialization)
                    implementation(ktorContentNegotiation)
                    implementation(ktorLogging)

                    implementation(ktxSerialization)
                    implementation(ktxCoroutine)
                    implementation(ktxDateTime)

                    api(kermitLogger)
                    implementation(kotlin("stdlib-common"))
                }
            }
        }

        val androidMain by getting {
            dependencies {
                with(Dependencies) {
                    implementation(ktorOkhttp)
                    implementation(ktorAndroid)
                    implementation(koinAndroid)
                }
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        //val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            //iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation(Dependencies.ktorDarwin)
                implementation(Dependencies.ktorIos)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }

        val iosX64Test by getting
        val iosArm64Test by getting
        //val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            //iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

// add support for kotlin extension function and sealed class to enum swift
kswift {
    install(dev.icerock.moko.kswift.plugin.feature.SealedToSwiftEnumFeature)
    install(dev.icerock.moko.kswift.plugin.feature.PlatformExtensionFunctionsFeature)
}

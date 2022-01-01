buildscript {
    val kotlinVersion: String by project
    println(kotlinVersion)

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${kotlinVersion}")
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("io.insert-koin:koin-gradle-plugin:${Versions.koin}")

        val kmpNativeCoroutinesVersion = if (kotlinVersion == "1.6.0") "0.9.0-new-mm" else "0.8.0"
        classpath("com.rickclephas.kmp:kmp-nativecoroutines-gradle-plugin:$kmpNativeCoroutinesVersion")
    }

}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
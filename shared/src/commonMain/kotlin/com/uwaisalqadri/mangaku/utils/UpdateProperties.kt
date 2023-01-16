package com.uwaisalqadri.mangaku.utils

import co.touchlab.kermit.Logger
import com.soywiz.korio.file.VfsFile
import com.soywiz.korio.file.std.*
import kotlinx.coroutines.runBlocking

fun updateProperties(stage: EnvStage) {
    // /Volumes/Extreme SSD/AndroidStudioProjects/MangaKu/gradle.properties
    val rootFile = rootLocalVfs["gradle.properties"]
    val resourcesFile = resourcesVfs["gradle.properties"]
    val appFile = applicationVfs["gradle.properties"]
    val userHomeFile = userHomeVfs["gradle.properties"]
    val tempFile = tempVfs["gradle.properties"]
    val cacheFile = cacheVfs["gradle.properties"]
    val localCurrentFile = localCurrentDirVfs

    runBlocking {
        rootFile.writeString(createGradleProperties(stage))
    }

    Logger.d { "rootFile $rootFile \n" }
    Logger.d { "resourcesFile $resourcesFile \n" }
    Logger.d { "appFile $appFile \n" }
    Logger.d { "userHomeFile $userHomeFile \n" }
    Logger.d { "tempFile $tempFile \n" }
    Logger.d { "cacheFile $cacheFile \n" }
    Logger.d { "localCurrentFile $localCurrentFile \n" }
}

enum class EnvStage(val stage: String) {
    DEV("dev"),
    PROD("release")
}

private fun createGradleProperties(stage: EnvStage): String {
    return """
        #Gradle
        org.gradle.jvmargs=-Xmx2048M -Dkotlin.daemon.jvm.options\="-Xmx2048M"

        #Kotlin
        kotlin.code.style=official

        #Android
        android.useAndroidX=true

        kotlin.native.binary.memoryModel=experimental
        kotlin.native.binary.freezing=disabled

        buildkonfig.flavor=${stage.name}
    """.trimIndent()
}
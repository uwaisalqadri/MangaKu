package com.uwaisalqadri.mangaapp.di

import co.touchlab.kermit.CommonLogger
import co.touchlab.kermit.Kermit
import com.uwaisalqadri.mangaapp.data.souce.remote.ApiService
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(networkModule)
    }

fun initKoin() = initKoin {} // for iOS

val networkModule = module {
    single { ApiService(get()) }
    single { createJson() }
    single { createHttpClient(get()) }
    single { Kermit(CommonLogger()) }
}

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

fun createHttpClient(json: Json) = HttpClient {
    install(JsonFeature) {
        serializer = KotlinxSerializer(json)
    }

    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.INFO

    }
}
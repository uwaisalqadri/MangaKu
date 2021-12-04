package com.uwaisalqadri.mangaku.di

import com.uwaisalqadri.mangaku.data.souce.local.entity.*
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(ktorModule, realmModule, repositoryModule, useCaseModule)
    }

fun initKoin() = initKoin {} // for iOS


fun createRealmDatabase(): Realm {
    val configuration = RealmConfiguration(schema = setOf(
        MangaObject::class,
        AttributesObject::class,
        CoverImageObject::class,
        PosterImageObject::class,
        TitlesObject::class
    ))

    return Realm(configuration)
}

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

fun createKtorClient(json: Json) = HttpClient {
    defaultRequest {
        headers {
            append(HttpHeaders.Accept, "application/vnd.api+json")
            append(HttpHeaders.ContentType, "application/vnd.api+json")
        }
    }

    install(JsonFeature) {
        serializer = KotlinxSerializer(json)
    }

    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.INFO
    }
}
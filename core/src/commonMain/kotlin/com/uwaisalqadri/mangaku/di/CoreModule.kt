package com.uwaisalqadri.mangaku.di

import co.touchlab.kermit.CommonLogger
import co.touchlab.kermit.Kermit
import co.touchlab.kermit.Logger as KermitLogger
import com.uwaisalqadri.mangaku.data.souce.local.MangaLocalDataSource
import com.uwaisalqadri.mangaku.data.souce.local.entity.*
import com.uwaisalqadri.mangaku.data.souce.remote.MangaRemoteDataSource
import com.uwaisalqadri.mangaku.di.feature.mangaModule
import com.uwaisalqadri.mangaku.utils.CustomLogger
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
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}): KoinApplication {
    val koinApplication = startKoin {
        appDeclaration()
        modules(
            ktorModule,
            realmModule,
            mangaModule
        )
    }

    return koinApplication
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
        level = LogLevel.ALL
    }
}


val realmModule = module {
    single { MangaLocalDataSource(get()) }
    single { createRealmDatabase() }
}

val ktorModule = module {
    single { MangaRemoteDataSource(get()) }
    single { createJson() }
    single { createKtorClient(get()) }
    // single { CommonLogger() }
}
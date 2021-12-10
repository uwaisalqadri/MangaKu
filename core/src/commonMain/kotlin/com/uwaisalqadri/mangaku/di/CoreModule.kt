package com.uwaisalqadri.mangaku.di

import com.uwaisalqadri.mangaku.data.souce.local.DefaultMangaLocalDataSource
import com.uwaisalqadri.mangaku.data.souce.local.entity.*
import com.uwaisalqadri.mangaku.data.souce.remote.DefaultMangaRemoteDataSource
import com.uwaisalqadri.mangaku.di.feature.mangaModule
import com.uwaisalqadri.mangaku.utils.Constants
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
    val configuration = RealmConfiguration.with(schema = setOf(
        MangaObject::class,
        AttributesObject::class,
        CoverImageObject::class,
        PosterImageObject::class,
        TitlesObject::class
    ))

    return Realm.open(configuration = configuration)
}

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

fun createKtorClient(json: Json) = HttpClient {
    defaultRequest {
//        host = Constants.baseUrl
//
//        url {
//            protocol = URLProtocol.HTTPS
//        }

        headers {
            append(HttpHeaders.Accept, "application/vnd.api+json")
            append(HttpHeaders.ContentType, "application/vnd.api+json")
        }
    }

    install(JsonFeature) {
        serializer = KotlinxSerializer(json)
    }

    install(HttpTimeout) {
        requestTimeoutMillis = 60000
        connectTimeoutMillis = 60000
        socketTimeoutMillis = 60000
    }

    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }
}


val realmModule = module {
    single { DefaultMangaLocalDataSource(get()) }
    single { createRealmDatabase() }
}

val ktorModule = module {
    single { DefaultMangaRemoteDataSource(get()) }
    single { createJson() }
    single { createKtorClient(get()) }
    // single { CommonLogger() }
}
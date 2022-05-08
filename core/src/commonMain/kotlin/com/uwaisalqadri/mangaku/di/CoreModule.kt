package com.uwaisalqadri.mangaku.di

import com.uwaisalqadri.mangaku.data.souce.local.MangaPersistenceContainer
import com.uwaisalqadri.mangaku.data.souce.local.entity.*
import com.uwaisalqadri.mangaku.data.souce.remote.MangaApi
import com.uwaisalqadri.mangaku.data.souce.remote.response.ApiException
import com.uwaisalqadri.mangaku.di.feature.mangaModule
import com.uwaisalqadri.mangaku.utils.Constants
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
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
    install(ContentNegotiation) {
        json()
    }

    defaultRequest {
        url {
            protocol = URLProtocol.HTTPS
            host = Constants.baseUrl

            header(HttpHeaders.Accept, "application/vnd.api+json")
            header(HttpHeaders.ContentType, "application/vnd.api+json")
        }
    }

    install(HttpTimeout) {
        this.requestTimeoutMillis = 60000
        this.connectTimeoutMillis = 60000
        this.socketTimeoutMillis = 60000
    }

    HttpResponseValidator {
        handleResponseExceptionWithRequest { exception, _ ->
            when (exception) {
                is ServerResponseException -> {
                    val serverResponseResponse = exception.response
                    val serverResponseExceptionText = serverResponseResponse.bodyAsText()
                    val apiException = json.decodeFromString(ApiException.serializer(), serverResponseExceptionText)
                    throw apiException
                }
                is ClientRequestException -> {
                    val exceptionResponse = exception.response
                    val exceptionResponseText = exceptionResponse.bodyAsText()
                    val apiException = json.decodeFromString(ApiException.serializer(), exceptionResponseText)
                    throw apiException
                }
                else -> {
                    return@handleResponseExceptionWithRequest
                }
            }
        }
    }

    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }

}


val realmModule = module {
    single { MangaPersistenceContainer(get()) }
    single { createRealmDatabase() }
}

val ktorModule = module {
    single { MangaApi(get()) }
    single { createJson() }
    single { createKtorClient(get()) }
}
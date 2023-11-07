package com.uwaisalqadri.mangaku.di

import com.uwaisalqadri.mangaku.data.souce.local.MangaPersistenceContainer
import com.uwaisalqadri.mangaku.data.souce.local.entity.*
import com.uwaisalqadri.mangaku.data.souce.remote.MangaApi
import com.uwaisalqadri.mangaku.data.souce.remote.response.ApiException
import com.uwaisalqadri.mangaku.di.feature.mangaModule
import com.uwaisalqadri.mangaku.utils.Configs
import com.uwaisalqadri.mangaku.utils.ktorEngineModule
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}): KoinApplication {
    return startKoin {
        appDeclaration()
        modules(
            ktorModule,
            ktorEngineModule(),
            realmModule,
            mangaModule
        )
    }
}

val realmModule = module {
    single { MangaPersistenceContainer(get()) }
    single { createRealmDatabase() }
}

val ktorModule = module {
    single { MangaApi(get()) }
    single { createJson() }
    single { createKtorClient(get(), get()) }
}

fun createRealmDatabase(): Realm {
    val configuration = RealmConfiguration.create(schema = setOf(
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

fun createKtorClient(httpClientEngine: HttpClientEngine, json: Json) = HttpClient(httpClientEngine) {
    install(ContentNegotiation) {
        json(json = json)
    }

    defaultRequest {
        url {
            protocol = URLProtocol.HTTPS
            host = Configs.BASE_URL

            headers {
                append(HttpHeaders.Accept, "application/vnd.api+json")
                append(HttpHeaders.ContentType, "application/vnd.api+json")
            }
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
                    val apiException: ApiException = json.decodeFromString(serverResponseExceptionText)
                    throw apiException
                }
                is ClientRequestException -> {
                    val exceptionResponse = exception.response
                    val exceptionResponseText = exceptionResponse.bodyAsText()
                    val apiException: ApiException = json.decodeFromString(exceptionResponseText)
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
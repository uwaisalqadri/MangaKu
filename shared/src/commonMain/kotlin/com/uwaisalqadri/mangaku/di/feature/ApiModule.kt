package com.uwaisalqadri.mangaku.di.feature

import com.uwaisalqadri.mangaku.data.source.remote.MangaApiDataSourceImpl
import com.uwaisalqadri.mangaku.data.source.remote.response.ApiExceptionResponse
import com.uwaisalqadri.mangaku.utils.YamlResourceReader
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val apiModule = module {
    single { createJson() }
    single { createKtorClient(get(), get(), get()) }
    singleOf(::MangaApiDataSourceImpl)
    singleOf(::YamlResourceReader)
}

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

fun createKtorClient(httpClientEngine: HttpClientEngine, resourceReader: YamlResourceReader, json: Json) = HttpClient(httpClientEngine) {
//    val config = resourceReader.readAndDecodeResource(getStage().file, Configs.serializer())

    install(ContentNegotiation) {
        json(json = json)
    }

    defaultRequest {
        url {
            protocol = URLProtocol.HTTPS
            host = "kitsu.io"

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
                    val apiExceptionResponse = json.decodeFromString(ApiExceptionResponse.serializer(), serverResponseExceptionText)
                    throw apiExceptionResponse
                }
                is ClientRequestException -> {
                    val exceptionResponse = exception.response
                    val exceptionResponseText = exceptionResponse.bodyAsText()
                    val apiExceptionResponse = json.decodeFromString(ApiExceptionResponse.serializer(), exceptionResponseText)
                    throw apiExceptionResponse
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
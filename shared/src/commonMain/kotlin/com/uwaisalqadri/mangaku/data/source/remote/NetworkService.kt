package com.uwaisalqadri.mangaku.data.source.remote

import com.uwaisalqadri.mangaku.data.source.remote.response.ApiExceptionResponse
import com.uwaisalqadri.mangaku.di.inject
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

class NetworkService {
    suspend inline fun <reified T> connect(api: ApiFactory): T {
        try {
            val response: HttpResponse = httpClient.request {
                url(api.composedUrl)
                url { protocol = URLProtocol.HTTPS }
                method = api.method
                headers {
                    api.headers.forEach { (key, value) ->
                        append(key, value)
                    }
                }
                api.parameters.forEach { (key, value) ->
                    parameter(key, value)
                }
                api.body?.let { setBody(it) }
            }

            val jsonString = response.bodyAsText()
            println("[NETWORK][${response.status.value}] $api")
            println("📗 prettyPrinted JSON response:\n${prettyPrintJson(jsonString)}")

            return json.decodeFromString<T>(jsonString)
        } catch (e: Exception) {
            println("❌ [NETWORK][Error] ${e.message}")
            throw e
        }
    }

    val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
        useAlternativeNames = false
        prettyPrint = true
    }

    val httpClient: HttpClient by lazy  {
        val engine: HttpClientEngine = inject()
        HttpClient(engine) {
            install(ContentNegotiation) {
                json(json)
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
                            val apiExceptionResponse = json.decodeFromString(
                                ApiExceptionResponse.serializer(),
                                serverResponseExceptionText
                            )
                            throw apiExceptionResponse
                        }

                        is ClientRequestException -> {
                            val exceptionResponse = exception.response
                            val exceptionResponseText = exceptionResponse.bodyAsText()
                            val apiExceptionResponse = json.decodeFromString(
                                ApiExceptionResponse.serializer(),
                                exceptionResponseText
                            )
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
    }

    fun prettyPrintJson(jsonRaw: String): String {
        return try {
            val jsonElement = json.parseToJsonElement(jsonRaw)
            json.encodeToString(JsonElement.serializer(), jsonElement)
        } catch (e: Exception) {
            "❌ Failed to pretty print JSON: ${e.message}"
        }
    }
}
package com.uwaisalqadri.mangaapp.utils

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.http.*

/**
 * Created by Uwais Alqadri on July 22, 2021
 */
class HttpClientFactory {

	fun create() = HttpClient {
		defaultRequest {
			url {
				host = Constants.BASE_URL
				protocol = URLProtocol.HTTPS
			}
		}
		Json {
			serializer = KotlinxSerializer(
				json = kotlinx.serialization.json.Json(from = kotlinx.serialization.json.Json.Default) {
					ignoreUnknownKeys = true
					useArrayPolymorphism = true
				}
			)
		}
		Logging {
			logger = Logger.DEFAULT
			level = LogLevel.INFO
		}
	}
}
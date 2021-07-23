package com.uwaisalqadri.mangaapp.utils

import io.ktor.client.features.*
import io.ktor.utils.io.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

/**
 * Created by Uwais Alqadri on July 22, 2021
 */
internal sealed class Result<T> {
	data class Success<T>(val result: T) : Result<T>()
	data class Failure<T>(val error: T) : Result<T>()
}

internal suspend inline fun <T> request(block: () -> T) = try {
	Result.Success(block())
} catch (clientRequestException: ClientRequestException) {
	println(clientRequestException)
	Result.Failure(getError(clientRequestException.response.content)?.error)
} catch (t: Throwable) {
	print(t)
	Result.Failure(null)
}

internal suspend fun getError(responseContent: ByteReadChannel) = responseContent.readUTF8Line()?.let {
	Json { ignoreUnknownKeys = true }.decodeFromString(NetworkError.serializer(), it)
}

@Serializable
internal data class NetworkError(val error: String?)






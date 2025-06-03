package com.uwaisalqadri.mangaku.data.source.api

import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod

sealed class KitsuApi: ApiFactory {
    data object GetList: KitsuApi()
    data object Trending: KitsuApi()
    data class Search(val query: String): KitsuApi()
    data class Detail(val id: String): KitsuApi()

    override val path: String
        get() = when (this) {
            is GetList -> "api/edge/manga"
            is Trending -> "api/edge/trending/manga"
            is Detail -> "api/edge/manga/$id"
            is Search -> "api/edge/manga"
        }

    override val parameters: Map<String, Any>
        get() = when (this) {
            is Search -> query.takeIf { it.isNotEmpty() }?.let {
                mapOf("filter[text]" to it)
            } ?: emptyMap()
            else -> emptyMap()
        }

    override val baseUrl: String
        get() = "https://kitsu.io/"

    override val method: HttpMethod
        get() = HttpMethod.Get

    override val headers: Map<String, String>
        get() = mapOf(
            HttpHeaders.Accept to "application/vnd.api+json",
            HttpHeaders.ContentType to "application/vnd.api+json"
        )
}
package com.uwaisalqadri.mangaku.data.source.remote

import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod

sealed class MangaApi: ApiFactory {
    data object GetList: MangaApi()
    data object Trending: MangaApi()
    data class Search(val query: String): MangaApi()
    data class Detail(val id: String): MangaApi()

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
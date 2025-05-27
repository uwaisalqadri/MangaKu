package com.uwaisalqadri.mangaku.data.source.remote

import io.ktor.http.HttpMethod

interface ApiFactory {
    val path: String
    val parameters: Map<String, Any>
    val baseUrl: String
    val method: HttpMethod
    val headers: Map<String, String>
    val body: Any? get() = null

    val composedUrl: String
        get() {
            val queryString = parameters.entries.joinToString("&") {
                "${it.key}=${it.value}"
            }
            return "$baseUrl$path?$queryString"
        }
}

sealed class HttpMethodType {
    data object Get : HttpMethodType()
    data class Post(val body: Any? = null) : HttpMethodType()
    data object Put : HttpMethodType()
    data object Delete : HttpMethodType()
    data object Patch : HttpMethodType()

    val method: HttpMethod
        get() = when (this) {
            is Get -> HttpMethod.Get
            is Post -> HttpMethod.Post
            is Put -> HttpMethod.Put
            is Delete -> HttpMethod.Delete
            is Patch -> HttpMethod.Patch
        }

    val bodyOrNull: Any?
        get() = when (this) {
            is Post -> this.body
            else -> null
        }
}

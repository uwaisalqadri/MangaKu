package com.uwaisalqadri.mangaku.data.source.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class MangaItemResponse(
    val attributes: AttributesResponse? = null,
    val id: String,
    val type: String
)
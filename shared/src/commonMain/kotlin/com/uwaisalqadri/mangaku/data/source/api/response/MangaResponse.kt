package com.uwaisalqadri.mangaku.data.source.api.response

import kotlinx.serialization.Serializable

@Serializable
data class MangaResponse(
    val data: List<MangaItemResponse>
)
package com.uwaisalqadri.mangaku.data.source.api.response

import kotlinx.serialization.Serializable

@Serializable
data class MangaDetailResponse(
    val data: MangaItemResponse?
)

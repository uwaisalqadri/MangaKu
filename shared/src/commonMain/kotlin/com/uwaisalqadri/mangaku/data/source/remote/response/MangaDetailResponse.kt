package com.uwaisalqadri.mangaku.data.source.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class MangaDetailResponse(
    val data: MangaItemResponse?
)

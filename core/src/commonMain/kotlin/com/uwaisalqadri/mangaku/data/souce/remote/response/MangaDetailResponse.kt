package com.uwaisalqadri.mangaku.data.souce.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class MangaDetailResponse(
    val data: MangaItem?
)

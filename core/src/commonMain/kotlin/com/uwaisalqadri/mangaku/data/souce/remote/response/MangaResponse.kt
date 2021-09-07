package com.uwaisalqadri.mangaku.data.souce.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class MangaResponse(
    val data: List<MangaItem>
)
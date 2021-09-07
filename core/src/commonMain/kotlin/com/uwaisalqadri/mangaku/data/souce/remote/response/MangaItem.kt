package com.uwaisalqadri.mangaku.data.souce.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class MangaItem(
    val attributes: AttributesItem? = null,
    val id: String,
    val type: String
)
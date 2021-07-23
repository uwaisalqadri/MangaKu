package com.uwaisalqadri.mangaapp.data.souce.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class Titles(
    val en: String,
    val en_jp: String,
    val en_us: String,
    val ja_jp: String
)
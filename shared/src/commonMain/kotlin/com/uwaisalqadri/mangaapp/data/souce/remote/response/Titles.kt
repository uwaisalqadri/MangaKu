package com.uwaisalqadri.mangaapp.data.souce.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class Titles(
    var en: String = "",
    var en_jp: String = "",
    var en_us: String = "",
    var ja_jp: String = ""
)
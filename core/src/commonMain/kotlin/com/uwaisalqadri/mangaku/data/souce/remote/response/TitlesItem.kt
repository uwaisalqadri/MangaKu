package com.uwaisalqadri.mangaku.data.souce.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class TitlesItem(
    var en: String = "",
    var en_jp: String = "",
    var en_us: String = "",
    var ja_jp: String = ""
)
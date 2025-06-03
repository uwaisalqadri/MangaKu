package com.uwaisalqadri.mangaku.data.source.api.response

import kotlinx.serialization.Serializable

@Serializable
data class PosterImageResponse(
    val large: String? = "",
    val medium: String? = "",
    val original: String? = "",
    val small: String? = "",
    val tiny: String? = ""
)
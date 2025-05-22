package com.uwaisalqadri.mangaku.data.source.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class CoverImageResponse(
    val large: String? = "",
    val medium: String? = "",
    val original: String? = "",
    val small: String? = "",
    val tiny: String? = ""
)

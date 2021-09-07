package com.uwaisalqadri.mangaapp.data.souce.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class CoverImageItem(
    val large: String? = null,
    val medium: String? = null,
    val original: String? = null,
    val small: String? = null,
    val tiny: String? = null
)

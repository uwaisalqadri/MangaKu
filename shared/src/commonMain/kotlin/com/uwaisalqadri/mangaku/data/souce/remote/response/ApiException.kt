package com.uwaisalqadri.mangaku.data.souce.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiException(
    @SerialName("error")
    val errorTitle: String,
    @SerialName("error_description")
    val errorMessage: String
): Exception()
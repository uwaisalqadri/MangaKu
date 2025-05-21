package com.uwaisalqadri.mangaku.data.source.remote.response

import com.uwaisalqadri.mangaku.domain.base.ApiError
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiException(
    @SerialName("error")
    val errorTitle: String,
    @SerialName("error_description")
    val errorMessage: String
): Exception() {
    fun map(): ApiError {
        return ApiError(errorTitle, errorMessage)
    }
}
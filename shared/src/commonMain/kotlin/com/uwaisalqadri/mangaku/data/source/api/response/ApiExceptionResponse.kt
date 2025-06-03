package com.uwaisalqadri.mangaku.data.source.api.response

import com.uwaisalqadri.mangaku.domain.base.exception.ApiError
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiExceptionResponse(
    @SerialName("error")
    val errorTitle: String,
    @SerialName("error_description")
    val errorMessage: String
): Exception() {
    fun map(): ApiError {
        return ApiError(errorTitle, errorMessage)
    }
}
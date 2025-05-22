package com.uwaisalqadri.mangaku.domain.model

data class ApiError(
    val errorTitle: String,
    val errorMessage: String
): Exception()
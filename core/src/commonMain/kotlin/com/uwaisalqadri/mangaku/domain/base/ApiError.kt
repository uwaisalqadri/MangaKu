package com.uwaisalqadri.mangaku.domain.base

data class ApiError(
    val errorTitle: String,
    val errorMessage: String
): Exception()
package com.uwaisalqadri.mangaku.domain.base.exception

data class ApiError(
    val errorTitle: String,
    val errorMessage: String
): Exception()
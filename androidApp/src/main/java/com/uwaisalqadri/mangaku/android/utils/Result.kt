package com.uwaisalqadri.mangaku.android.utils

data class Result<T>(
    var data: T? = null,
    var loading: Boolean = false,
    var empty: Boolean = false,
    var error: Throwable? = null,
) {
    companion object {
        fun <T> loading(): Result<T> = Result(loading = true)
        fun <T> empty(): Result<T> = Result(empty = true)
        fun <T> success(data: T?): Result<T> = Result(data = data)
        fun <T> failed(error: Throwable): Result<T> = Result(error = error)
    }
}


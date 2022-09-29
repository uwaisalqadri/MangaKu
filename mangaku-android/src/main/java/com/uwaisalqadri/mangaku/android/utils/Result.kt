package com.uwaisalqadri.mangaku.android.utils

sealed class Result<T> {
    class Loading<T> : Result<T>()
    class Default<T> : Result<T>()
    class Empty<T> : Result<T>()
    data class Success<T>(val data: T) : Result<T>()
    data class Failure<T>(val throwable: Throwable?, val message: String?) : Result<T>()

    companion object {
        fun <T> loading(): Result<T> = Loading()
        fun <T> default(): Result<T> = Default()
        fun <T> success(data: T): Result<T> = Success(data)
        fun <T> empty(): Result<T> = Empty()
        fun <T> fail(throwable: Throwable, message: String?): Result<T> = Failure(throwable, message)
        fun <T> fail(message: String?): Result<T> = Failure(null, message)
    }
}


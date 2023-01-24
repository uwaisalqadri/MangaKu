package com.uwaisalqadri.mangaku.presentation

sealed class ViewState<T> {
    class Loading<T> : ViewState<T>()
    class Default<T> : ViewState<T>()
    class Empty<T> : ViewState<T>()
    data class Success<T>(val data: T) : ViewState<T>()
    data class Failure<T>(val throwable: Throwable?, val message: String?) : ViewState<T>()

    companion object {
        fun <T> loading(): ViewState<T> = Loading()
        fun <T> default(): ViewState<T> = Default()
        fun <T> success(data: T): ViewState<T> = Success(data)
        fun <T> empty(): ViewState<T> = Empty()
        fun <T> fail(throwable: Throwable, message: String?): ViewState<T> = Failure(throwable, message)
        fun <T> fail(message: String?): ViewState<T> = Failure(null, message)
    }
}


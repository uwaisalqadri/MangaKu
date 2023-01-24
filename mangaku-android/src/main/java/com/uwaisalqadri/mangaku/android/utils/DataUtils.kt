package com.uwaisalqadri.mangaku.android.utils

import com.uwaisalqadri.mangaku.presentation.ViewState

inline fun <reified T> getValue(resource: ViewState<T>?): T? {
    return if (resource is ViewState.Success) resource.data else null
}

inline fun <reified T> ViewState<T>.isLoading(): Boolean {
    return this is ViewState.Loading
}

inline fun <reified T> ViewState<T>.isEmpty(): Boolean {
    return (this is ViewState.Empty || this is ViewState.Default)
}
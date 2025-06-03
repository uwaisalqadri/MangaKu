package com.uwaisalqadri.mangaku.android.presentation.detail

sealed class DetailAction {
    data class GetManga(val id: String): DetailAction()
}
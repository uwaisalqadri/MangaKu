package com.uwaisalqadri.mangaku.presentation.detail

sealed class DetailEvent {
    data class GetManga(
        val id: String
    ): DetailEvent()
}
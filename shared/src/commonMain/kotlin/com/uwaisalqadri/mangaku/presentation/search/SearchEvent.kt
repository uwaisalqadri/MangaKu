package com.uwaisalqadri.mangaku.presentation.search

sealed class SearchEvent {

    data object Empty: SearchEvent()
    data class GetMangas(
        val query: String
    ): SearchEvent()
}
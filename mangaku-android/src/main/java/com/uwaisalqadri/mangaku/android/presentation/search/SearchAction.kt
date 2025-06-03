package com.uwaisalqadri.mangaku.android.presentation.search

sealed class SearchAction {
    data object Empty: SearchAction()
    data class GetMangas(val query: String): SearchAction()
}
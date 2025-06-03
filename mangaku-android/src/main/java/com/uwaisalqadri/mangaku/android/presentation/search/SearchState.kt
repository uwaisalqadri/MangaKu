package com.uwaisalqadri.mangaku.android.presentation.search

import com.uwaisalqadri.mangaku.domain.base.model.Manga

data class SearchState(
    val searchQuery: String = "",
    val items: List<Manga> = listOf(),
    val isLoading: Boolean = false,
    val errorMessage: String = "",
)
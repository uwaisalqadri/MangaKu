package com.uwaisalqadri.mangaku.presentation.search

import com.uwaisalqadri.mangaku.domain.model.Manga

data class SearchState(
    val mangas: List<Manga> = listOf(),
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
    val errorMessage: String = "",
)
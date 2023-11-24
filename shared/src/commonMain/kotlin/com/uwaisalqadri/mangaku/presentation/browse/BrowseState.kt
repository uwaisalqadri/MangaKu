package com.uwaisalqadri.mangaku.presentation.browse

import com.uwaisalqadri.mangaku.domain.model.Manga

data class BrowseState(
    val mangas: List<Manga> = listOf(),
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
    val errorMessage: String = ""
)
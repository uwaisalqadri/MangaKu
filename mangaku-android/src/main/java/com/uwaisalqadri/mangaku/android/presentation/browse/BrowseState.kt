package com.uwaisalqadri.mangaku.android.presentation.browse

import com.uwaisalqadri.mangaku.domain.base.model.Manga

data class BrowseState(
    val items: List<Manga> = listOf(),
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)
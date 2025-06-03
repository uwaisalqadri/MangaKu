package com.uwaisalqadri.mangaku.android.presentation.mymanga

import com.uwaisalqadri.mangaku.domain.base.model.Manga

data class MyMangaState(
    val items: List<Manga> = listOf(),
    val isFavorite: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)
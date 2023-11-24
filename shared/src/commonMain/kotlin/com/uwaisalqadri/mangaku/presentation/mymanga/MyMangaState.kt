package com.uwaisalqadri.mangaku.presentation.mymanga

import com.uwaisalqadri.mangaku.domain.model.Manga

data class MyMangaState(
    val mangas: List<Manga> = listOf(),
    val isFavorite: Boolean = false,
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
    val errorMessage: String = ""
)
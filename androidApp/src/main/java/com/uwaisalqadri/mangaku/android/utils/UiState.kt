package com.uwaisalqadri.mangaku.android.utils

import com.uwaisalqadri.mangaku.domain.model.Manga

data class UiState(
    val listManga: List<Manga> = emptyList(),
    val loading: Boolean = false,
    val refreshError: Boolean = false
)

data class DetailUiState(
    val manga: Manga = Manga(null, "", ""),
    val loading: Boolean = false,
    val refreshError: Boolean = false
)
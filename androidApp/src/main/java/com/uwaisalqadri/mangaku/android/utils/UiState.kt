package com.uwaisalqadri.mangaku.android.utils

import com.uwaisalqadri.mangaku.domain.model.Manga

data class UiState(
    val listManga: List<Manga> = emptyList(),
    val isLoading: Boolean = false,
    val refreshError: Boolean = false
)
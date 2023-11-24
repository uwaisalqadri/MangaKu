package com.uwaisalqadri.mangaku.presentation.detail

import com.uwaisalqadri.mangaku.domain.model.Manga

data class DetailState(
    val manga: Manga? = null,
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)
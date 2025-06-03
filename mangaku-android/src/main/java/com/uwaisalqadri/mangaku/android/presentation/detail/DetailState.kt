package com.uwaisalqadri.mangaku.android.presentation.detail

import com.uwaisalqadri.mangaku.domain.base.model.Manga

data class DetailState(
    val manga: Manga? = null,
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)
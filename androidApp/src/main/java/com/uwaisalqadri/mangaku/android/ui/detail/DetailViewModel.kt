package com.uwaisalqadri.mangaku.android.ui.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uwaisalqadri.mangaku.android.utils.UiState
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.usecase.detail.GetMangaDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel(
    private val detailUseCase: GetMangaDetailUseCase
) : ViewModel() {

    private val _manga = MutableStateFlow(Manga(null, "", ""))
    val manga: StateFlow<Manga> = _manga

    fun fetchDetailManga(mangaId: String) = viewModelScope.launch {
        detailUseCase(id = mangaId).collect { item ->
            if (item != null) _manga.value = item
        }
    }

}


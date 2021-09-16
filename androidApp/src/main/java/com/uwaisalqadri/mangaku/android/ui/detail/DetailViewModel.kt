package com.uwaisalqadri.mangaku.android.ui.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.usecase.detail.GetMangaDetailUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel(
    private val detailUseCase: GetMangaDetailUseCase
) : ViewModel() {

    val detailManga = mutableStateOf(Manga(null, "", ""))
    val loading = mutableStateOf(true)

    fun fetchDetailManga(mangaId: String) = viewModelScope.launch {
        detailUseCase(mangaId).collect { result ->
            if (result != null) {
                detailManga.value = result
                loading.value = false
            }
        }
    }

}


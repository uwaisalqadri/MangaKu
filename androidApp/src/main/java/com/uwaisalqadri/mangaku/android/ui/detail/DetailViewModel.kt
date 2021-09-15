package com.uwaisalqadri.mangaku.android.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uwaisalqadri.mangaku.android.utils.DetailUiState
import com.uwaisalqadri.mangaku.domain.usecase.detail.GetMangaDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel(
    private val detailUseCase: GetMangaDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState(loading = false))
    val uiState: StateFlow<DetailUiState> = _uiState

    fun fetchDetailManga(mangaId: String) = viewModelScope.launch {
        _uiState.value = DetailUiState(loading = true)

        detailUseCase(mangaId).collect { result ->
            if (result != null) _uiState.value = DetailUiState(manga = result)
        }
    }

}


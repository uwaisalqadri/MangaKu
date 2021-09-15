package com.uwaisalqadri.mangaku.android.ui.browse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uwaisalqadri.mangaku.android.utils.UiState
import com.uwaisalqadri.mangaku.domain.usecase.browse.GetMangaListUseCase
import com.uwaisalqadri.mangaku.domain.usecase.browse.GetMangaTrendingUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BrowseViewModel(
    private val listUseCase: GetMangaListUseCase,
    private val trendingUseCase: GetMangaTrendingUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(UiState(loading = true))
    val uiState: StateFlow<UiState> = _uiState

    init {
        fetchManga()
    }

    private fun fetchManga() = viewModelScope.launch {
        trendingUseCase().collect { result ->
            if (result.isNotEmpty()) _uiState.value = UiState(listManga = result)
        }
    }
}
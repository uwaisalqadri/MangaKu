package com.uwaisalqadri.mangaku.android.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uwaisalqadri.mangaku.android.utils.UiState
import com.uwaisalqadri.mangaku.domain.usecase.browse.GetMangaSearchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchUseCase: GetMangaSearchUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(UiState(loading = true))
    val uiState: StateFlow<UiState> = _uiState

    init {
        fetchSearchManga("Naruto")
    }

    private fun fetchSearchManga(query: String) = viewModelScope.launch {
        searchUseCase(query).collect { result ->
            if (result.isNotEmpty()) _uiState.value = UiState(listManga = result)
        }
    }

}
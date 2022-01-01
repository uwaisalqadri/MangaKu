package com.uwaisalqadri.mangaku.android.ui.browse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uwaisalqadri.mangaku.android.utils.UiState
import com.uwaisalqadri.mangaku.domain.usecase.browse.BrowseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BrowseViewModel(
    private val browseUseCase: BrowseUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(UiState(isLoading = false))
    val uiState: StateFlow<UiState> = _uiState

    init {
        getTrendingManga()
    }

    private fun getTrendingManga() = viewModelScope.launch {
        _uiState.value = UiState(isLoading = true)
        browseUseCase.getManga().collect { result ->
            if (result.isNotEmpty()) _uiState.value = UiState(listManga = result)
        }
    }
}
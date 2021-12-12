package com.uwaisalqadri.mangaku.android.ui.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uwaisalqadri.mangaku.android.utils.UiState
import com.uwaisalqadri.mangaku.domain.usecase.search.SearchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchUseCase: SearchUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(UiState(isLoading = false))
    val uiState: StateFlow<UiState> = _uiState
    val query = mutableStateOf("")

    fun onQueryChanged(query: String) {
        this.query.value = query
        getSearchManga(query)
    }

    fun getSearchManga(query: String) = viewModelScope.launch {
        _uiState.value = UiState(isLoading = true)
        searchUseCase.getSearchManga(query).collect { result ->
            if (result.isNotEmpty()) _uiState.value = UiState(listManga = result)
        }
    }
}













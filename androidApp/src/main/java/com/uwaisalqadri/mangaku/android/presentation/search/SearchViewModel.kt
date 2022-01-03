package com.uwaisalqadri.mangaku.android.presentation.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uwaisalqadri.mangaku.android.utils.Result
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.usecase.search.SearchUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchUseCase: SearchUseCase
): ViewModel() {

    private val _searchManga = MutableStateFlow<Result<List<Manga>>>(Result.success(listOf()))
    val searchManga = _searchManga.asStateFlow()

    val query = mutableStateOf("")

    fun onQueryChanged(query: String) {
        this.query.value = query
        getSearchManga(query)
    }

    fun getSearchManga(query: String) = viewModelScope.launch {
        _searchManga.value = Result.loading()
        searchUseCase.getSearchManga(query)
            .catch { cause: Throwable ->
                _searchManga.value = Result.failed(cause)
            }
            .collect { result ->
                if (query.isNotEmpty()) _searchManga.value = Result.success(result)
                else _searchManga.value = Result.success(listOf())
            }
    }
}













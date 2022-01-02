package com.uwaisalqadri.mangaku.android.presentation.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uwaisalqadri.mangaku.android.utils.Result
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.usecase.search.SearchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchUseCase: SearchUseCase
): ViewModel() {

    private val _searchManga: MutableStateFlow<Result<List<Manga>>> = MutableStateFlow(Result())
    val searchManga: StateFlow<Result<List<Manga>>> = _searchManga

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
                if (result.isNotEmpty()) _searchManga.value = Result.success(result)
                else _searchManga.value = Result.empty()
            }
    }
}













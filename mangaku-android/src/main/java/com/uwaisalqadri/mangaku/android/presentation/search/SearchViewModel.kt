package com.uwaisalqadri.mangaku.android.presentation.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uwaisalqadri.mangaku.android.utils.Result
import com.uwaisalqadri.mangaku.android.utils.collectFlow
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.usecase.search.SearchUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchUseCase: SearchUseCase
): ViewModel() {

    private val _searchManga = MutableStateFlow<Result<List<Manga>>>(Result.default())
    val searchManga: StateFlow<Result<List<Manga>>> = _searchManga.asStateFlow()

    val query = mutableStateOf("")

    fun onQueryChanged(query: String) {
        this.query.value = query
        getSearchManga(query)
    }

    fun getSearchManga(query: String) = viewModelScope.launch {
        _searchManga.value = Result.loading()
        collectFlow(_searchManga) {
            searchUseCase.getSearchManga(query)
        }
    }
}













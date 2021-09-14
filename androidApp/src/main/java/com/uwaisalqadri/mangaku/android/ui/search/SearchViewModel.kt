package com.uwaisalqadri.mangaku.android.ui.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.usecase.browse.GetMangaListUseCase
import com.uwaisalqadri.mangaku.domain.usecase.browse.GetMangaSearchUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchUseCase: GetMangaSearchUseCase
): ViewModel() {

    val searchManga: MutableState<List<Manga>> = mutableStateOf(ArrayList())
    val isLoaded = mutableStateOf(false)

    init {
        fetchSearchManga("naruto")
    }

    private fun fetchSearchManga(query: String) = viewModelScope.launch {
        searchUseCase(query).collect { result ->
            isLoaded.value = true
            if (result.isNotEmpty()) {
                searchManga.value = result
            }
        }
    }

}
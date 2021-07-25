package com.uwaisalqadri.mangaapp.android.ui.browse

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uwaisalqadri.mangaapp.data.souce.remote.response.Manga
import com.uwaisalqadri.mangaapp.domain.usecase.list.GetMangaListUseCase
import com.uwaisalqadri.mangaapp.domain.usecase.search.GetMangaSearchUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BrowseViewModel(
    private val searchUseCase: GetMangaSearchUseCase,
    private val listUseCase: GetMangaListUseCase
): ViewModel() {

    val mangas: MutableState<List<Manga>> = mutableStateOf(ArrayList())
    val loading = mutableStateOf(false)

    init {
        fetchMangas()
    }

    private fun fetchMangas() = viewModelScope.launch {
        listUseCase.execute().collect { result ->
            if (!result.isNullOrEmpty()) {
                mangas.value = result
            } else {
                Log.d("fetchMangas", "empty")
            }
        }
    }
}
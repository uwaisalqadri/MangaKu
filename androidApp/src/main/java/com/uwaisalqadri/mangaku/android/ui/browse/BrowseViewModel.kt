package com.uwaisalqadri.mangaku.android.ui.browse

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.usecase.browse.GetMangaListUseCase
import com.uwaisalqadri.mangaku.domain.usecase.browse.GetMangaTrendingUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BrowseViewModel(
    private val listUseCase: GetMangaListUseCase,
    private val trendingUseCase: GetMangaTrendingUseCase
): ViewModel() {

    val mangas: MutableState<List<Manga>> = mutableStateOf(ArrayList())
    val trendingManga: MutableState<List<Manga>> = mutableStateOf(ArrayList())
    val isLoaded = mutableStateOf(false)

    init {
        fetchManga()
    }

    private fun fetchManga() = viewModelScope.launch {
        trendingUseCase().collect { result ->
            isLoaded.value = true
            if (result.isNotEmpty()) {
                trendingManga.value = result
            }
        }
    }


}
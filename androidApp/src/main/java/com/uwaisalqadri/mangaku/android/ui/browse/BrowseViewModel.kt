package com.uwaisalqadri.mangaku.android.ui.browse

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.usecase.browse.GetMangaListUseCase
import com.uwaisalqadri.mangaku.domain.usecase.browse.GetMangaTrendingUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.CreateMangaFavoriteUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BrowseViewModel(
    private val listUseCase: GetMangaListUseCase,
    private val trendingUseCase: GetMangaTrendingUseCase,
    private val favoriteUseCase: CreateMangaFavoriteUseCase
): ViewModel() {

    val mangas: MutableState<List<Manga>> = mutableStateOf(ArrayList())
    val trendingMangas: MutableState<List<Manga>> = mutableStateOf(ArrayList())
    val loading = mutableStateOf(true)

    init {
        fetchMangas()
    }

    fun addFavoriteManga(manga: Manga) {
        favoriteUseCase.add(manga)
    }

    fun removeFavoriteManga(mangaId: Int) {
        favoriteUseCase.delete(mangaId)
    }

    private fun fetchMangas() = viewModelScope.launch {
        trendingUseCase().collect { result ->
            if (!result.isNullOrEmpty()) {
                trendingMangas.value = result
                loading.value = false
            } else {
                loading.value = false
                Log.d("fetchMangas", "empty")
            }
        }
    }
}
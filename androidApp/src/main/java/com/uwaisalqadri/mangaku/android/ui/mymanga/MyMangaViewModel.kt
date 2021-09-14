package com.uwaisalqadri.mangaku.android.ui.mymanga

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.usecase.browse.GetMangaListUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.CreateMangaFavoriteUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.GetMangaFavoriteUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MyMangaViewModel(
    private val createFavoriteUseCase: CreateMangaFavoriteUseCase,
    private val favoriteUseCase: GetMangaFavoriteUseCase
): ViewModel() {

    val favoriteManga: MutableState<List<Manga>> = mutableStateOf(ArrayList())
    val isLoaded = mutableStateOf(false)

    init {
        fetchFavoriteManga()
    }

    fun addFavoriteManga(manga: Manga) {
        val ids = mutableListOf<String>()

        favoriteManga.value.forEach {
            ids.add(it.id)
        }

        if (!ids.contains(manga.id)) {
            createFavoriteUseCase.add(manga)
        }

        fetchFavoriteManga()
    }

    fun removeFavoriteManga(mangaId: String) {
        createFavoriteUseCase.delete(mangaId)
    }

    private fun fetchFavoriteManga() = viewModelScope.launch {
        favoriteUseCase().collect { result ->
            isLoaded.value = true
            if (result.isNotEmpty()) {
                favoriteManga.value = result
            }
        }
    }

}
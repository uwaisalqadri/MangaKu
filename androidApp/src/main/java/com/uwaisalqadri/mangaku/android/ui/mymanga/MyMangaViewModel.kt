package com.uwaisalqadri.mangaku.android.ui.mymanga

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uwaisalqadri.mangaku.android.utils.UiState
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.CreateMangaFavoriteUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.GetMangaFavoriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MyMangaViewModel(
    private val createFavoriteUseCase: CreateMangaFavoriteUseCase,
    private val favoriteUseCase: GetMangaFavoriteUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(UiState(loading = true))
    private val _favoriteManga: MutableState<List<Manga>> = mutableStateOf(emptyList())
    val uiState: StateFlow<UiState> = _uiState

    init {
        fetchFavoriteManga()
    }

    fun addFavoriteManga(manga: Manga) {
        val ids = mutableListOf<String>()

        _favoriteManga.value.forEach {
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
            if (result.isNotEmpty()) {
                _uiState.value = UiState(listManga = result)
                _favoriteManga.value = result
            }
        }
    }

}
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

    private val ids = mutableListOf<String>()

    init {
        fetchFavoriteManga()
    }

    fun addFavoriteManga(manga: Manga, isSuccess: (String, Boolean) -> Unit) {
        _favoriteManga.value.forEach {
            ids.add(it.id)
        }

        if (!ids.contains(manga.id)) {
            createFavoriteUseCase.add(manga)
            isSuccess("Added to Favorite!", true)
        } else {
            createFavoriteUseCase.delete(manga.id)
            isSuccess("Removed from Favorite!", false)
        }

    }

    fun checkFavorite(mangaId: String, isFavorite: (Boolean) -> Unit) {
        _uiState.value.listManga.forEach {
            ids.add(it.id)
            val listId = ids.joinToString(",")
            isFavorite(listId.contains(mangaId))
        }
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
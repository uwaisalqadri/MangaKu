package com.uwaisalqadri.mangaku.android.ui.mymanga

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uwaisalqadri.mangaku.android.utils.FavState
import com.uwaisalqadri.mangaku.android.utils.UiState
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.GetMangaFavoriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MyMangaViewModel(
    private val favoriteUseCase: GetMangaFavoriteUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(UiState(loading = true))
    val uiState: StateFlow<UiState> = _uiState

    private val _favState = MutableLiveData<FavState>()
    val favState: LiveData<FavState> = _favState


    fun addFavoriteManga(manga: Manga) {
        favoriteUseCase.add(manga)
        _favState.value = FavState(addFavorite = true)
    }

    fun removeFavoriteManga(mangaId: String) {
        favoriteUseCase.delete(mangaId)
        _favState.value = FavState(removeFavorite = true)
    }

    fun checkFavorite(mangaId: String) = viewModelScope.launch {
        favoriteUseCase.getById(mangaId).collect { result ->
            result.forEach {
                _favState.value = FavState(favMangaFound = it.id == mangaId)
            }
        }
    }

    fun fetchFavoriteManga() = viewModelScope.launch {
        favoriteUseCase.get().collect { result ->
            if (result.isNotEmpty()) {
                _uiState.value = UiState(listManga = result)
            }
        }
    }

}
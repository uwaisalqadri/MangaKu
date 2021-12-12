package com.uwaisalqadri.mangaku.android.ui.mymanga

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uwaisalqadri.mangaku.android.utils.FavState
import com.uwaisalqadri.mangaku.android.utils.UiState
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.MyMangaUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MyMangaViewModel(
    private val myMangaUseCase: MyMangaUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(UiState(isLoading = true))
    val uiState: StateFlow<UiState> = _uiState

    private val _favState = MutableLiveData<FavState>()
    val favState: LiveData<FavState> = _favState


    fun addMyManga(manga: Manga) {
        myMangaUseCase.addManga(manga)
        _favState.value = FavState(addFavorite = true)
    }

    fun deleteMyManga(mangaId: String) {
        myMangaUseCase.deleteManga(mangaId)
        _favState.value = FavState(removeFavorite = true)
    }

    fun checkFavorite(mangaId: String) = viewModelScope.launch {
        myMangaUseCase.getMyMangaById(mangaId).collect { result ->
            result.forEach {
                _favState.value = FavState(favMangaFound = it.id == mangaId)
            }
        }
    }

    fun getMyManga() = viewModelScope.launch {
        myMangaUseCase.getMyManga().collect { result ->
            if (result.isNotEmpty()) {
                _uiState.value = UiState(listManga = result)
            }
        }
    }

}
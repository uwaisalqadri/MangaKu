package com.uwaisalqadri.mangaku.android.presentation.mymanga

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uwaisalqadri.mangaku.android.utils.FavState
import com.uwaisalqadri.mangaku.android.utils.Result
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.MyMangaUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MyMangaViewModel(
    private val myMangaUseCase: MyMangaUseCase
): ViewModel() {

    private val _myManga: MutableStateFlow<Result<List<Manga>>> = MutableStateFlow(Result())
    val myManga: StateFlow<Result<List<Manga>>> = _myManga

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
        _myManga.value = Result.loading()
        myMangaUseCase.getMyManga()
            .catch { cause: Throwable ->
                _myManga.value = Result.failed(cause)
            }
            .collect { result ->
                if (result.isNotEmpty()) {
                    _myManga.value = Result.success(result)
                } else {
                    _myManga.value = Result.empty()
                }
            }
    }

}
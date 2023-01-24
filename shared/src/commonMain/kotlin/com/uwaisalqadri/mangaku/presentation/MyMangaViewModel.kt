package com.uwaisalqadri.mangaku.presentation

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmm.viewmodel.stateIn
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.MyMangaUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class MyMangaViewModel: KMMViewModel(), KoinComponent {

    private val myMangaUseCase: MyMangaUseCase by inject()

    private val _myManga = MutableStateFlow<ViewState<List<Manga>>>(ViewState.default())
    private val _favState = MutableStateFlow(FavState())

    @NativeCoroutinesState
    val myManga: StateFlow<ViewState<List<Manga>>> = _myManga
        .asStateFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), ViewState.default())

    @NativeCoroutinesState
    val favState: StateFlow<FavState> = _favState
        .asStateFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), FavState())

    fun addMyManga(manga: Manga) {
        myMangaUseCase.addManga(manga)
        _favState.value = FavState(addFavorite = true)
        checkFavorite(manga.id)
    }

    fun deleteMyManga(mangaId: String) {
        myMangaUseCase.deleteManga(mangaId)
        _favState.value = FavState(removeFavorite = true)
        checkFavorite(mangaId)
    }

    fun checkFavorite(mangaId: String) = viewModelScope.coroutineScope.launch {
        myMangaUseCase.getMyMangaById(mangaId).collect { result ->
            _favState.value = FavState(favMangaFound = result.map { it.id }.contains(mangaId))
        }
    }

    fun getMyManga() = viewModelScope.coroutineScope.launch {
        _myManga.value = ViewState.loading()
        collectFlow(_myManga) {
            myMangaUseCase.getMyManga()
        }
    }

}
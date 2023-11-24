package com.uwaisalqadri.mangaku.presentation.mymanga

import co.touchlab.kermit.Logger
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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class MyMangaViewModel: KMMViewModel(), KoinComponent {

    private val myMangaUseCase: MyMangaUseCase by inject()

    private val _state = MutableStateFlow(MyMangaState())

    @NativeCoroutinesState
    val state: StateFlow<MyMangaState> = _state.asStateFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), MyMangaState())

    init {
        onTriggerEvent(MyMangaEvent.GetMyMangas)
    }

    fun onTriggerEvent(event: MyMangaEvent) {
        when (event) {
            is MyMangaEvent.GetMyMangas -> {
                getMyManga()
            }
            is MyMangaEvent.Empty -> {
                _state.value = MyMangaState(isEmpty = true)
            }
            is MyMangaEvent.CheckFavorite -> {
                checkFavorite(event.mangaId)
            }
            is MyMangaEvent.AddFavorite -> {
                addMyManga(event.manga)
            }
            is MyMangaEvent.DeleteFavorite -> {
                deleteMyManga(event.mangaId)
            }
        }
    }

    private fun addMyManga(manga: Manga) {
        myMangaUseCase.addManga(manga)
        checkFavorite(manga.id)
    }

    private fun deleteMyManga(mangaId: String) {
        myMangaUseCase.deleteManga(mangaId)
        checkFavorite(mangaId)
    }

    private fun checkFavorite(mangaId: String) = viewModelScope.coroutineScope.launch {
        myMangaUseCase.getMyMangaById(mangaId).collect { result ->
            Logger.d { "isContain this ${mangaId} ${result.map { it.id }.contains(mangaId)}" }
            _state.value = _state.value.copy(isFavorite = result.map { it.id }.contains(mangaId))
        }
    }

    private fun getMyManga() = viewModelScope.coroutineScope.launch {
        _state.value = _state.value.copy(isLoading = true)

        myMangaUseCase.getMyManga().catch { cause: Throwable ->
            _state.value = _state.value.copy(errorMessage = cause.message.orEmpty())
        }.collect {
            if (it.isEmpty()) _state.value = MyMangaState(isEmpty = true)
            else _state.value = MyMangaState(mangas = it)
        }
    }
}
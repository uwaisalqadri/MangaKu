package com.uwaisalqadri.mangaku.presentation.mymanga

import co.touchlab.kermit.Logger
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.base.execute
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.AddMangaUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.DeleteMangaUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.GetMyMangaByIdUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.GetMyMangaUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

open class MyMangaViewModel(
    private val getUseCase: GetMyMangaUseCase,
    private val getByIdUseCase: GetMyMangaByIdUseCase,
    private val addUseCase: AddMangaUseCase,
    private val deleteUseCase: DeleteMangaUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MyMangaState())
    val state: StateFlow<MyMangaState> = _state.asStateFlow()

    fun send(event: MyMangaEvent) {
        when (event) {
            is MyMangaEvent.Empty -> {
                _state.update { it.copy(isEmpty = true) }
            }

            is MyMangaEvent.GetMyMangas -> {
                getMyManga()
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
        addUseCase.execute(manga)
        checkFavorite(manga.id)
    }

    private fun deleteMyManga(mangaId: String) {
        deleteUseCase.execute(mangaId)
        checkFavorite(mangaId)
    }

    private fun checkFavorite(mangaId: String) = viewModelScope.launch {
        getByIdUseCase.execute(mangaId).collect { result ->
            val isFavorite = result.any { it.id == mangaId }
            Logger.d { "checkFavorite(mangaId: $mangaId): $isFavorite || $result" }
            _state.update { it.copy(isFavorite = isFavorite) }
        }
    }

    private fun getMyManga() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }

        getUseCase.execute()
            .catch { cause ->
                _state.update { it.copy(errorMessage = cause.message.orEmpty(), isLoading = false) }
            }
            .collect { result ->
                _state.update {
                    if (result.isEmpty()) {
                        it.copy(isEmpty = true)
                    } else {
                        it.copy(
                            mangas = result,
                            isEmpty = false
                        )
                    }
                }
            }
    }
}
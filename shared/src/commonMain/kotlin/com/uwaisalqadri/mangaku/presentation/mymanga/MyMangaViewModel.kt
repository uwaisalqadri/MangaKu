package com.uwaisalqadri.mangaku.presentation.mymanga

import co.touchlab.kermit.Logger
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import com.uwaisalqadri.mangaku.di.inject
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.execute
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.AddMangaUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.DeleteMangaUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.GetMyMangaByIdUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.GetMyMangaUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update

open class MyMangaViewModel: ViewModel() {

    private val getUseCase: GetMyMangaUseCase = inject()
    private val getByIdUseCase: GetMyMangaByIdUseCase = inject()
    private val addUseCase: AddMangaUseCase = inject()
    private val deleteUseCase: DeleteMangaUseCase = inject()

    private val _state = MutableStateFlow(MyMangaState())
    @NativeCoroutinesState
    val state = _state.asStateFlow()

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
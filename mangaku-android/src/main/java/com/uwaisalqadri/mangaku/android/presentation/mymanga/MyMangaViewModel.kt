package com.uwaisalqadri.mangaku.android.presentation.mymanga

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.uwaisalqadri.mangaku.di.inject
import com.uwaisalqadri.mangaku.domain.base.model.Manga
import com.uwaisalqadri.mangaku.domain.utils.execute
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.AddMangaUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.DeleteMangaUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.GetMyMangaByIdUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.GetMyMangaUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

open class MyMangaViewModel: ViewModel() {

    private val getUseCase: GetMyMangaUseCase = inject()
    private val getByIdUseCase: GetMyMangaByIdUseCase = inject()
    private val addUseCase: AddMangaUseCase = inject()
    private val deleteUseCase: DeleteMangaUseCase = inject()

    private val _state = MutableStateFlow(MyMangaState())
    val state = _state.asStateFlow()

    fun send(action: MyMangaAction) {
        when (action) {
            is MyMangaAction.GetMyMangas -> {
                getMyManga()
            }

            is MyMangaAction.CheckFavorite -> {
                viewModelScope.launch {
                    checkFavorite(action.mangaId)
                }
            }

            is MyMangaAction.AddFavorite -> {
                addMyManga(action.manga)
            }

            is MyMangaAction.DeleteFavorite -> {
                deleteMyManga(action.mangaId)
            }
        }
    }

    private fun addMyManga(manga: Manga) = viewModelScope.launch {
        addUseCase.execute(manga).collect()
        checkFavorite(manga.id)
    }

    private fun deleteMyManga(mangaId: String) = viewModelScope.launch {
        deleteUseCase.execute(mangaId).collect()
        checkFavorite(mangaId)
    }

    private suspend fun checkFavorite(mangaId: String) {
        getByIdUseCase.execute(mangaId)
            .catch { cause ->
                _state.update {
                    it.copy(
                        errorMessage = cause.message.orEmpty(),
                        isLoading = false
                    )
                }
            }
            .collect { result ->
                val isFavorite = result.any { it.id == mangaId }
                _state.update { it.copy(isFavorite = isFavorite) }
            }
    }

    private fun getMyManga() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }

        getUseCase.execute()
            .catch { cause ->
                _state.update {
                    it.copy(
                        errorMessage = cause.message.orEmpty(),
                        isLoading = false
                    )
                }
            }
            .collect { result ->
                _state.update {
                    it.copy(
                        items = result.ifEmpty { emptyList() },
                        isLoading = false
                    )
                }
            }
    }
}
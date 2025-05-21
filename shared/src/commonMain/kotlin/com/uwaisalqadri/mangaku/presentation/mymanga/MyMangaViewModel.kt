package com.uwaisalqadri.mangaku.presentation.mymanga

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import com.rickclephas.kmp.observableviewmodel.stateIn
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.usecase.common.execute
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.AddMangaUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.DeleteMangaUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.GetMyMangaByIdUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.GetMyMangaUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class MyMangaViewModel: ViewModel(), KoinComponent {

    private val getUseCase: GetMyMangaUseCase by inject()
    private val getByIdUseCase: GetMyMangaByIdUseCase by inject()
    private val addUseCase: AddMangaUseCase by inject()
    private val deleteUseCase: DeleteMangaUseCase by inject()

    private val _state = MutableStateFlow(MyMangaState())
    val state: StateFlow<MyMangaState> = _state.asStateFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), MyMangaState())

    fun send(event: MyMangaEvent) {
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
        addUseCase.execute(manga)
        checkFavorite(manga.id)
    }

    private fun deleteMyManga(mangaId: String) {
        deleteUseCase.execute(mangaId)
        checkFavorite(mangaId)
    }

    private fun checkFavorite(mangaId: String) = viewModelScope.launch {
        getByIdUseCase.execute(mangaId).collect { result ->
            _state.value = _state.value.copy(isFavorite = result.map { it.id }.contains(mangaId))
        }
    }

    private fun getMyManga() = viewModelScope.launch {
        _state.value = _state.value.copy(isLoading = true)

        getUseCase.execute().catch { cause: Throwable ->
            _state.value = _state.value.copy(errorMessage = cause.message.orEmpty())
        }.collect {
            if (it.isEmpty()) _state.value = MyMangaState(isEmpty = true)
            else _state.value = MyMangaState(mangas = it)
        }
    }
}
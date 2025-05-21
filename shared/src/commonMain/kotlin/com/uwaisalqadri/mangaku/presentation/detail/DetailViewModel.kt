package com.uwaisalqadri.mangaku.presentation.detail

import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import com.rickclephas.kmp.observableviewmodel.stateIn
import com.uwaisalqadri.mangaku.domain.usecase.detail.DetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class DetailViewModel: ViewModel(), KoinComponent {

    private val detailUseCase: DetailUseCase by inject()
    val mangaId = MutableStateFlow("")

    private val _state: MutableStateFlow<DetailState> = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), DetailState())

    fun send(event: DetailEvent) {
        when (event) {
            is DetailEvent.GetManga -> {
                getDetailManga(event.id)
            }
        }
    }
    
    private fun getDetailManga(mangaId: String) = viewModelScope.launch {
        _state.value = _state.value.copy(isLoading = true)

        detailUseCase.execute(mangaId).catch { cause: Throwable ->
            _state.value = _state.value.copy(errorMessage = cause.message.orEmpty())
        }.collect {
            _state.value = DetailState(manga = it)
        }
    }

}


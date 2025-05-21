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
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class DetailViewModel(
    private val detailUseCase: DetailUseCase
) : ViewModel(), KoinComponent {

    val mangaId = MutableStateFlow("")

    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state.asStateFlow()

    fun send(event: DetailEvent) {
        when (event) {
            is DetailEvent.GetManga -> {
                getDetailManga(event.id)
            }
        }
    }

    private fun getDetailManga(mangaId: String) = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }

        detailUseCase.execute(mangaId)
            .catch { cause ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = cause.message.orEmpty()
                    )
                }
            }
            .collect { result ->
                _state.update { DetailState(manga = result) }
            }
    }
}

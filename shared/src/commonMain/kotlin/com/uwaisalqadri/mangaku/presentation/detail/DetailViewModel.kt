package com.uwaisalqadri.mangaku.presentation.detail

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmm.viewmodel.stateIn
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.uwaisalqadri.mangaku.domain.usecase.detail.DetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class DetailViewModel: KMMViewModel(), KoinComponent {

    private val detailUseCase: DetailUseCase by inject()
    
    private val _state: MutableStateFlow<DetailState> = MutableStateFlow(DetailState())

    val mangaId = MutableStateFlow("")

    @NativeCoroutinesState
    val state: StateFlow<DetailState> = _state
        .asStateFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), DetailState())

    fun onTriggerEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.GetManga -> {
                getDetailManga(event.id)
            }
        }
    }
    
    private fun getDetailManga(mangaId: String) = viewModelScope.coroutineScope.launch {
        _state.value = _state.value.copy(isLoading = true)

        detailUseCase.getDetailManga(mangaId).catch { cause: Throwable ->
            _state.value = _state.value.copy(errorMessage = cause.message.orEmpty())
        }.collect {
            _state.value = DetailState(manga = it)
        }
    }

}


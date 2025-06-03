package com.uwaisalqadri.mangaku.android.presentation.detail

import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import com.uwaisalqadri.mangaku.di.inject
import com.uwaisalqadri.mangaku.domain.usecase.detail.DetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update

open class DetailViewModel: ViewModel() {

    private val detailUseCase: DetailUseCase = inject()

    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    fun send(action: DetailAction) {
        when (action) {
            is DetailAction.GetManga -> {
                getDetailManga(action.id)
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
                _state.update {
                    it.copy(
                        manga = result,
                        isLoading = false
                    )
                }
            }
    }
}

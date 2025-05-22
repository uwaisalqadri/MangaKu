package com.uwaisalqadri.mangaku.presentation.browse

import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import com.uwaisalqadri.mangaku.domain.usecase.browse.BrowseUseCase
import com.uwaisalqadri.mangaku.domain.base.execute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

open class BrowseViewModel(
    private val browseUseCase: BrowseUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(BrowseState())
    val state: StateFlow<BrowseState> = _state.asStateFlow()

    fun send(event: BrowseEvent) {
        when (event) {
            BrowseEvent.GetMangas -> getTrendingManga()
        }
    }

    private fun getTrendingManga() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }

        browseUseCase.execute()
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
                    if (result.isEmpty()) {
                        BrowseState(isEmpty = true)
                    } else {
                        BrowseState(mangas = result)
                    }
                }
            }
    }
}
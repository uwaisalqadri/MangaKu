package com.uwaisalqadri.mangaku.presentation.browse

import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import com.rickclephas.kmp.observableviewmodel.stateIn
import com.uwaisalqadri.mangaku.domain.usecase.browse.BrowseUseCase
import com.uwaisalqadri.mangaku.domain.usecase.common.execute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class BrowseViewModel: ViewModel(), KoinComponent {

    private val browseUseCase: BrowseUseCase by inject()

    private val _state: MutableStateFlow<BrowseState> = MutableStateFlow(BrowseState())
    val state: StateFlow<BrowseState> = _state
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), BrowseState())

    fun send(event: BrowseEvent) {
        when (event) {
            is BrowseEvent.GetMangas -> {
                getTrendingManga()
            }
        }
    }

    private fun getTrendingManga() = viewModelScope.launch {
        _state.value = _state.value.copy(isLoading = true)

        browseUseCase.execute().catch { cause: Throwable ->
            _state.value = _state.value.copy(errorMessage = cause.message.orEmpty())
        }.collect {
            if (it.isEmpty()) _state.value = BrowseState(isEmpty = true)
            else _state.value = BrowseState(mangas = it)
        }
    }
}
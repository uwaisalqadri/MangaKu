package com.uwaisalqadri.mangaku.presentation.browse

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmm.viewmodel.stateIn
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.uwaisalqadri.mangaku.domain.usecase.browse.BrowseUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class BrowseViewModel: KMMViewModel(), KoinComponent {

    private val browseUseCase: BrowseUseCase by inject()
    private val _state: MutableStateFlow<BrowseState> = MutableStateFlow(BrowseState())

    @NativeCoroutinesState
    val state: StateFlow<BrowseState> = _state
        .asStateFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), BrowseState())

    init {
        onTriggerEvent(BrowseEvent.GetMangas)
    }

    fun onTriggerEvent(event: BrowseEvent) {
        when (event) {
            is BrowseEvent.GetMangas -> {
                getTrendingManga()
            }
        }
    }

    private fun getTrendingManga() = viewModelScope.coroutineScope.launch {
        _state.value = _state.value.copy(isLoading = true)

        browseUseCase.getTrendingManga().catch { cause: Throwable ->
            _state.value = _state.value.copy(errorMessage = cause.message.orEmpty())
        }.collect {
            if (it.isEmpty()) _state.value = BrowseState(isEmpty = true)
            else _state.value = BrowseState(mangas = it)
        }
    }
}
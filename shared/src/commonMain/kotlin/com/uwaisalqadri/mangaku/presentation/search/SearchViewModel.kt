package com.uwaisalqadri.mangaku.presentation.search

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmm.viewmodel.stateIn
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.uwaisalqadri.mangaku.domain.usecase.search.SearchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class SearchViewModel: KMMViewModel(), KoinComponent {

    private val searchUseCase: SearchUseCase by inject()

    private val _state: MutableStateFlow<SearchState> = MutableStateFlow(SearchState())

    val searchQuery: MutableStateFlow<String> = MutableStateFlow("")

    @NativeCoroutinesState
    val state: StateFlow<SearchState> = _state
        .asStateFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), SearchState())

    fun onTriggerEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.GetMangas -> {
                searchQuery.value = event.query
                getSearchManga(query = event.query)
            }
            is SearchEvent.Empty -> {
                searchQuery.value = ""
                _state.value = SearchState(isEmpty = true)
            }
        }
    }

    private fun getSearchManga(query: String) = viewModelScope.coroutineScope.launch {
        _state.value = _state.value.copy(isLoading = true)

        searchUseCase.getSearchManga(query).catch { cause: Throwable ->
            _state.value = _state.value.copy(errorMessage = cause.message.orEmpty())
        }.collect {
            _state.value = _state.value.copy(
                mangas = it,
                isEmpty = it.isEmpty(),
                isLoading = false
            )
        }
    }
}
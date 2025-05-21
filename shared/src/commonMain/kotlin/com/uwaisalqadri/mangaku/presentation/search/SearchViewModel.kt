package com.uwaisalqadri.mangaku.presentation.search

import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import com.rickclephas.kmp.observableviewmodel.stateIn
import com.uwaisalqadri.mangaku.domain.usecase.search.SearchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class SearchViewModel: ViewModel(), KoinComponent {

    private val searchUseCase: SearchUseCase by inject()
    val searchQuery: MutableStateFlow<String> = MutableStateFlow("")

    private val _state: MutableStateFlow<SearchState> = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), SearchState())

    fun send(event: SearchEvent) {
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

    private fun getSearchManga(query: String) = viewModelScope.launch {
        _state.value = _state.value.copy(isLoading = true)

        searchUseCase.execute(query).catch { cause: Throwable ->
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
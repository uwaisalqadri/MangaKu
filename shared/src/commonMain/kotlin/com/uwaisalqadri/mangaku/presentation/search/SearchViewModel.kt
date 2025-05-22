package com.uwaisalqadri.mangaku.presentation.search

import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import com.uwaisalqadri.mangaku.domain.usecase.search.SearchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

open class SearchViewModel(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    val searchQuery = MutableStateFlow("")

    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state.asStateFlow()

    fun send(event: SearchEvent) {
        when (event) {
            is SearchEvent.GetMangas -> {
                searchQuery.value = event.query
                getSearchManga(event.query)
            }

            SearchEvent.Empty -> {
                searchQuery.value = ""
                _state.value = SearchState(isEmpty = true)
            }
        }
    }

    private fun getSearchManga(query: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            searchUseCase.execute(query)
                .catch { cause ->
                    _state.update {
                        it.copy(
                            errorMessage = cause.message.orEmpty(),
                            isLoading = false
                        )
                    }
                }
                .collect { result ->
                    _state.update {
                        it.copy(
                            mangas = result,
                            isEmpty = result.isEmpty(),
                            isLoading = false
                        )
                    }
                }
        }
    }
}
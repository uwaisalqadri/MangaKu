package com.uwaisalqadri.mangaku.presentation.search

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import com.uwaisalqadri.mangaku.di.inject
import com.uwaisalqadri.mangaku.domain.usecase.search.SearchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

open class SearchViewModel: ViewModel() {

    private val searchUseCase: SearchUseCase = inject()

    private val _state = MutableStateFlow(SearchState())
    @NativeCoroutinesState
    val state = _state.asStateFlow()

    fun send(event: SearchEvent) {
        when (event) {
            is SearchEvent.GetMangas -> {
                _state.update { it.copy(searchQuery = event.query) }
                getSearchManga(event.query)
            }

            is SearchEvent.Empty -> {
                _state.update { it.copy(isEmpty = true, searchQuery = "") }
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
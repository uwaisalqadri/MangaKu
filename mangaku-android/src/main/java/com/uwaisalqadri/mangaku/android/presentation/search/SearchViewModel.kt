package com.uwaisalqadri.mangaku.android.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uwaisalqadri.mangaku.di.inject
import com.uwaisalqadri.mangaku.domain.usecase.search.SearchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

open class SearchViewModel: ViewModel() {

    private val searchUseCase: SearchUseCase = inject()

    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    fun send(action: SearchAction) {
        when (action) {
            is SearchAction.GetMangas -> {
                _state.update { it.copy(searchQuery = action.query) }
                getSearchManga(action.query)
            }

            is SearchAction.Empty -> {
                _state.update { it.copy(searchQuery = "") }
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
                            items = result.ifEmpty { emptyList() },
                            isLoading = false
                        )
                    }
                }
        }
    }
}
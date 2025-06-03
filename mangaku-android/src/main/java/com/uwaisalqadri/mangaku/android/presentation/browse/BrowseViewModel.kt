package com.uwaisalqadri.mangaku.android.presentation.browse

import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import com.uwaisalqadri.mangaku.di.inject
import com.uwaisalqadri.mangaku.domain.utils.execute
import com.uwaisalqadri.mangaku.domain.usecase.browse.BrowseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update

open class BrowseViewModel : ViewModel() {

    private val browseUseCase: BrowseUseCase = inject()

    private val _state = MutableStateFlow(BrowseState())
    val state = _state.asStateFlow()

    fun send(action: BrowseAction) {
        when (action) {
            BrowseAction.GetMangas -> getTrendingManga()
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
                    it.copy(
                        isLoading = false,
                        items = result.ifEmpty { emptyList() }
                    )
                }
            }
    }
}
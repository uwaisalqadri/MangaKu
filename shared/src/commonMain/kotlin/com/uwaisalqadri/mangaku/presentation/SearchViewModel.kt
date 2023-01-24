package com.uwaisalqadri.mangaku.presentation

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmm.viewmodel.stateIn
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.usecase.search.SearchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class SearchViewModel: KMMViewModel(), KoinComponent {

    private val searchUseCase: SearchUseCase by inject()

    private val _searchManga = MutableStateFlow<ViewState<List<Manga>>>(ViewState.default())

    @NativeCoroutinesState
    val searchManga: StateFlow<ViewState<List<Manga>>> = _searchManga
        .asStateFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), ViewState.default())

    fun getSearchManga(query: String) = viewModelScope.coroutineScope.launch {
        _searchManga.value = ViewState.loading()
        collectFlow(_searchManga) {
            searchUseCase.getSearchManga(query)
        }
    }
}













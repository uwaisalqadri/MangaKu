package com.uwaisalqadri.mangaku.presentation

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmm.viewmodel.stateIn
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.usecase.browse.BrowseUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class BrowseViewModel: KMMViewModel(), KoinComponent {

    private val browseUseCase: BrowseUseCase by inject()
    private val _trendingManga = MutableStateFlow<ViewState<List<Manga>>>(ViewState.default())

    @NativeCoroutinesState
    val trendingManga: StateFlow<ViewState<List<Manga>>> = _trendingManga
        .asStateFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), ViewState.default())

    fun getTrendingManga() = viewModelScope.coroutineScope.launch {
        _trendingManga.value = ViewState.loading()
        collectFlow(_trendingManga) {
            browseUseCase.getTrendingManga()
        }
    }
}
package com.uwaisalqadri.mangaku.presentation

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmm.viewmodel.stateIn
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.usecase.detail.DetailUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class DetailViewModel: KMMViewModel(), KoinComponent {

    private val detailUseCase: DetailUseCase by inject()

    private val _detailManga = MutableStateFlow<ViewState<Manga>>(ViewState.default())

    @NativeCoroutinesState
    val detailManga: StateFlow<ViewState<Manga>> = _detailManga
        .asStateFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), ViewState.default())

    fun getDetailManga(mangaId: String) = viewModelScope.coroutineScope.launch {
        _detailManga.value = ViewState.loading()
        collectFlow(_detailManga) {
            detailUseCase.getDetailManga(mangaId).map {
                Manga(attributes = it?.attributes, id = it?.id.orEmpty(), type = it?.type.orEmpty())
            }
        }
    }

}


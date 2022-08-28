package com.uwaisalqadri.mangaku.android.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uwaisalqadri.mangaku.android.utils.Result
import com.uwaisalqadri.mangaku.android.utils.collectFlow
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.usecase.detail.DetailUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailViewModel(
    private val detailUseCase: DetailUseCase
) : ViewModel() {

    private val _detailManga = MutableStateFlow<Result<Manga>>(Result.loading())
    val detailManga: StateFlow<Result<Manga>> = _detailManga.asStateFlow()

    fun getDetailManga(mangaId: String) = viewModelScope.launch {
        detailUseCase.getDetailManga(mangaId)
            .catch { cause: Throwable ->
                _detailManga.value = Result.failed(cause)
            }
            .collect { result ->
                if (result != null) {
                    _detailManga.value = Result.success(result)
                } else {
                    _detailManga.value = Result.empty()
                }
            }
    }

}


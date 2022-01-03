package com.uwaisalqadri.mangaku.android.presentation.browse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.usecase.browse.BrowseUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import com.uwaisalqadri.mangaku.android.utils.Result as Result

class BrowseViewModel(
    private val browseUseCase: BrowseUseCase
): ViewModel() {

    private val _trendingManga = MutableStateFlow<Result<List<Manga>>>(Result())
    val trendingManga = _trendingManga.asStateFlow()

    init {
        getTrendingManga()
    }

    private fun getTrendingManga() = viewModelScope.launch {
        _trendingManga.value = Result.loading()
        browseUseCase.getTrendingManga()
            .catch { cause: Throwable ->
                _trendingManga.value = Result.failed(cause)
            }
            .collect { result ->
                if (result.isNotEmpty())
                    _trendingManga.value = Result.success(result)
            }
    }
}
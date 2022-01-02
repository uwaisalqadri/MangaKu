package com.uwaisalqadri.mangaku.android.presentation.browse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.usecase.browse.BrowseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.uwaisalqadri.mangaku.android.utils.Result as Result

class BrowseViewModel(
    private val browseUseCase: BrowseUseCase
): ViewModel() {

    private val _trendingManga: MutableStateFlow<Result<List<Manga>>> = MutableStateFlow(Result())
    val trendingManga: StateFlow<Result<List<Manga>>> = _trendingManga

    init {
        getTrendingManga()
    }

    private fun getTrendingManga() = viewModelScope.launch {
        _trendingManga.value = Result.loading()
        browseUseCase.getManga()
            .catch { cause: Throwable ->
                _trendingManga.value = Result.failed(cause)
            }
            .collect { result ->
                if (result.isNotEmpty())
                    _trendingManga.value = Result.success(result)
            }
    }
}
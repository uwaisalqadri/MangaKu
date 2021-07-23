package com.uwaisalqadri.mangaapp.android.ui.browse

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uwaisalqadri.mangaapp.data.souce.remote.response.Manga
import com.uwaisalqadri.mangaapp.domain.usecase.manga_list.GetMangaListUseCase
import com.uwaisalqadri.mangaapp.domain.usecase.manga_search.GetMangaSearchUseCase
import com.uwaisalqadri.mangaapp.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BrowseViewModel(
    private val searcUseCase: GetMangaSearchUseCase,
    private val listUseCase: GetMangaListUseCase
): ViewModel() {

    private val _listData = MutableLiveData<Resource<List<Manga>>>()
    val listData = _listData

    fun getListManga() = viewModelScope.launch {
        _listData.postValue(Resource.Loading)
        listUseCase.execute().collect {
            _listData.value = it
        }
    }
}
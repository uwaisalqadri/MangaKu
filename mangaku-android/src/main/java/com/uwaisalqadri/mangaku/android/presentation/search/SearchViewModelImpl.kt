package com.uwaisalqadri.mangaku.android.presentation.search

import androidx.compose.runtime.mutableStateOf
import com.uwaisalqadri.mangaku.presentation.SearchViewModel

class SearchViewModelImpl: SearchViewModel() {

    val query = mutableStateOf("")

    fun onQueryChanged(query: String) {
        this.query.value = query
        getSearchManga(query)
    }
}
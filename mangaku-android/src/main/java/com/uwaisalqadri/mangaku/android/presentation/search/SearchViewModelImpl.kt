package com.uwaisalqadri.mangaku.android.presentation.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.uwaisalqadri.mangaku.domain.usecase.search.SearchUseCase
import com.uwaisalqadri.mangaku.presentation.SearchViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.inject

class SearchQueryHandler: ViewModel(), KoinComponent {

    val searchViewModel: SearchViewModel by inject()
    val query = mutableStateOf("")

    fun onQueryChanged(query: String) {
        this.query.value = query
        searchViewModel.getSearchManga(query)
    }
}
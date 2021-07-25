package com.uwaisalqadri.mangaapp.android.ui.saved

import androidx.lifecycle.ViewModel
import com.uwaisalqadri.mangaapp.domain.usecase.list.GetMangaListUseCase

class SavedViewModel(
    private val listUseCase: GetMangaListUseCase
): ViewModel() {

}
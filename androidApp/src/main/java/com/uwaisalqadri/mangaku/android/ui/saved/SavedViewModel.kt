package com.uwaisalqadri.mangaku.android.ui.saved

import androidx.lifecycle.ViewModel
import com.uwaisalqadri.mangaku.domain.usecase.list.GetMangaListUseCase

class SavedViewModel(
    private val listUseCase: GetMangaListUseCase
): ViewModel() {

}
package com.uwaisalqadri.mangaku.android.ui.mymanga

import androidx.lifecycle.ViewModel
import com.uwaisalqadri.mangaku.domain.usecase.browse.GetMangaListUseCase

class MyMangaViewModel(
    private val listUseCase: GetMangaListUseCase
): ViewModel() {
}
package com.uwaisalqadri.mangaapp.android.ui.mymanga

import androidx.lifecycle.ViewModel
import com.uwaisalqadri.mangaapp.domain.usecase.manga_list.GetMangaListUseCase

class MyMangaViewModel(
    private val listUseCase: GetMangaListUseCase
): ViewModel() {
}
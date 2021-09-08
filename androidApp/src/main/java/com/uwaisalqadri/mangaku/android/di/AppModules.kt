package com.uwaisalqadri.mangaku.android.di

import com.uwaisalqadri.mangaku.android.ui.browse.BrowseViewModel
import com.uwaisalqadri.mangaku.android.ui.mymanga.MyMangaViewModel
import com.uwaisalqadri.mangaku.android.ui.saved.SavedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { BrowseViewModel(get(), get(), get()) }
    viewModel { MyMangaViewModel(get()) }
    viewModel { SavedViewModel(get()) }
}
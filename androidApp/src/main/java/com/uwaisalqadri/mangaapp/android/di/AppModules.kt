package com.uwaisalqadri.mangaapp.android.di

import com.uwaisalqadri.mangaapp.android.ui.browse.BrowseViewModel
import com.uwaisalqadri.mangaapp.android.ui.mymanga.MyMangaViewModel
import com.uwaisalqadri.mangaapp.android.ui.saved.SavedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { BrowseViewModel(get(), get()) }
    viewModel { MyMangaViewModel(get()) }
    viewModel { SavedViewModel(get()) }
}
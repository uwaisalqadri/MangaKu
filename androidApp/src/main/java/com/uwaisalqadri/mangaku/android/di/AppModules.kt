package com.uwaisalqadri.mangaku.android.di

import com.uwaisalqadri.mangaku.android.ui.browse.BrowseViewModel
import com.uwaisalqadri.mangaku.android.ui.detail.DetailViewModel
import com.uwaisalqadri.mangaku.android.ui.mymanga.MyMangaViewModel
import com.uwaisalqadri.mangaku.android.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { BrowseViewModel(get(), get()) }
    viewModel { MyMangaViewModel(get(), get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
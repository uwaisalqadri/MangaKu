package com.uwaisalqadri.mangaku.android.di

import com.uwaisalqadri.mangaku.android.ui.browse.BrowseViewModel
import com.uwaisalqadri.mangaku.android.ui.detail.DetailViewModel
import com.uwaisalqadri.mangaku.android.ui.mymanga.MyMangaViewModel
import com.uwaisalqadri.mangaku.android.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    viewModel { BrowseViewModel(get()) }
    viewModel { MyMangaViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
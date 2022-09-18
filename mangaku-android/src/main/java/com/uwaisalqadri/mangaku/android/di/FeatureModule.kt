package com.uwaisalqadri.mangaku.android.di

import com.uwaisalqadri.mangaku.android.presentation.browse.BrowseViewModel
import com.uwaisalqadri.mangaku.android.presentation.detail.DetailViewModel
import com.uwaisalqadri.mangaku.android.presentation.mymanga.MyMangaViewModel
import com.uwaisalqadri.mangaku.android.presentation.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    viewModel { BrowseViewModel(get()) }
    viewModel { MyMangaViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
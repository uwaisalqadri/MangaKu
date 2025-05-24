package com.uwaisalqadri.mangaku.android.di

import com.uwaisalqadri.mangaku.presentation.browse.BrowseViewModel
import com.uwaisalqadri.mangaku.presentation.detail.DetailViewModel
import com.uwaisalqadri.mangaku.presentation.mymanga.MyMangaViewModel
import com.uwaisalqadri.mangaku.presentation.search.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    viewModel { BrowseViewModel(get()) }
    viewModel { MyMangaViewModel(get(), get(), get(), get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
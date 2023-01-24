package com.uwaisalqadri.mangaku.android.di

import com.uwaisalqadri.mangaku.android.presentation.search.SearchViewModelImpl
import com.uwaisalqadri.mangaku.presentation.DetailViewModel
import com.uwaisalqadri.mangaku.presentation.MyMangaViewModel
import com.uwaisalqadri.mangaku.presentation.SearchViewModel
import com.uwaisalqadri.mangaku.presentation.BrowseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    viewModel { BrowseViewModel() }
    viewModel { MyMangaViewModel() }
    viewModel { SearchViewModelImpl() }
    viewModel { DetailViewModel() }
}
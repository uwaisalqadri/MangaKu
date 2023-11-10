package com.uwaisalqadri.mangaku.android.di

import com.uwaisalqadri.mangaku.android.presentation.search.SearchQueryHandler
import com.uwaisalqadri.mangaku.presentation.BrowseViewModel
import com.uwaisalqadri.mangaku.presentation.DetailViewModel
import com.uwaisalqadri.mangaku.presentation.MyMangaViewModel
import com.uwaisalqadri.mangaku.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureModule = module {
    viewModelOf(::BrowseViewModel)
    viewModelOf(::MyMangaViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::DetailViewModel)
    viewModelOf(::SearchQueryHandler)
}
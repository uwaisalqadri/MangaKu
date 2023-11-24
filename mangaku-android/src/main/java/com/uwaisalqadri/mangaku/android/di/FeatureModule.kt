package com.uwaisalqadri.mangaku.android.di

import com.uwaisalqadri.mangaku.presentation.browse.BrowseViewModel
import com.uwaisalqadri.mangaku.presentation.detail.DetailViewModel
import com.uwaisalqadri.mangaku.presentation.mymanga.MyMangaViewModel
import com.uwaisalqadri.mangaku.presentation.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureModule = module {
    viewModelOf(::BrowseViewModel)
    viewModelOf(::MyMangaViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::DetailViewModel)
}
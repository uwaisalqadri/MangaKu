package com.uwaisalqadri.mangaku.di

import com.uwaisalqadri.mangaku.domain.usecase.browse.BrowseUseCase
import com.uwaisalqadri.mangaku.domain.usecase.detail.DetailUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.MyMangaUseCase
import com.uwaisalqadri.mangaku.domain.usecase.search.SearchUseCase
import org.koin.core.Koin
import org.koin.core.KoinApplication

// Koin utilities for iOS injection

fun KoinApplication.Companion.start(): KoinApplication = initKoin {}

val Koin.browseUseCase: BrowseUseCase
    get() = get()

val Koin.searchUseCase: SearchUseCase
    get() = get()

val Koin.detailUseCase: DetailUseCase
    get() = get()

val Koin.myMangaUseCase: MyMangaUseCase
    get() = get()
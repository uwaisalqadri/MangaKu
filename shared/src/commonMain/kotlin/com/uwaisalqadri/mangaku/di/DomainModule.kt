package com.uwaisalqadri.mangaku.di

import com.uwaisalqadri.mangaku.domain.usecase.browse.BrowseUseCase
import com.uwaisalqadri.mangaku.domain.usecase.browse.TrendingUseCase
import com.uwaisalqadri.mangaku.domain.usecase.detail.DetailUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.AddMangaUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.DeleteMangaUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.GetMyMangaByIdUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.GetMyMangaUseCase
import com.uwaisalqadri.mangaku.domain.usecase.search.SearchUseCase
import org.koin.dsl.module

val domainModule = module {
    single { BrowseUseCase(get()) }
    single { TrendingUseCase(get()) }
    single { DetailUseCase(get()) }
    single { SearchUseCase(get()) }
    single { AddMangaUseCase(get()) }
    single { DeleteMangaUseCase(get()) }
    single { GetMyMangaByIdUseCase(get()) }
    single { GetMyMangaUseCase(get()) }
}
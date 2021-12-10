package com.uwaisalqadri.mangaku.di.feature

import com.uwaisalqadri.mangaku.data.repository.DefaultMangaRepository
import com.uwaisalqadri.mangaku.data.souce.local.DefaultMangaLocalDataSource
import com.uwaisalqadri.mangaku.data.souce.local.MangaLocalDataSource
import com.uwaisalqadri.mangaku.data.souce.remote.DefaultMangaRemoteDataSource
import com.uwaisalqadri.mangaku.data.souce.remote.MangaRemoteDataSource
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import com.uwaisalqadri.mangaku.domain.usecase.browse.BrowseInteractor
import com.uwaisalqadri.mangaku.domain.usecase.browse.BrowseUseCase
import com.uwaisalqadri.mangaku.domain.usecase.detail.DetailInteractor
import com.uwaisalqadri.mangaku.domain.usecase.detail.DetailUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.MyMangaInteractor
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.MyMangaUseCase
import com.uwaisalqadri.mangaku.domain.usecase.search.SearchInteractor
import com.uwaisalqadri.mangaku.domain.usecase.search.SearchUseCase
import org.koin.dsl.module

val mangaModule = module {
    single<MangaRemoteDataSource> { DefaultMangaRemoteDataSource(get()) }
    single<MangaLocalDataSource> { DefaultMangaLocalDataSource(get()) }
    single<MangaRepository> { DefaultMangaRepository(get(), get()) }
    single<BrowseUseCase> { BrowseInteractor(get()) }
    single<DetailUseCase> { DetailInteractor(get()) }
    single<MyMangaUseCase> { MyMangaInteractor(get()) }
    single<SearchUseCase> { SearchInteractor(get()) }
}
package com.uwaisalqadri.mangaku.di.feature

import com.uwaisalqadri.mangaku.data.repository.MangaDataStore
import com.uwaisalqadri.mangaku.data.souce.local.MangaPersistenceContainer
import com.uwaisalqadri.mangaku.data.souce.local.MangaPersistence
import com.uwaisalqadri.mangaku.data.souce.remote.MangaApi
import com.uwaisalqadri.mangaku.data.souce.remote.MangaApiClient
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
    single<MangaApiClient> { MangaApi(get()) }
    single<MangaPersistence> { MangaPersistenceContainer(get()) }
    single<MangaRepository> { MangaDataStore(get(), get()) }
    single<BrowseUseCase> { BrowseInteractor(get()) }
    single<DetailUseCase> { DetailInteractor(get()) }
    single<MyMangaUseCase> { MyMangaInteractor(get()) }
    single<SearchUseCase> { SearchInteractor(get()) }
}
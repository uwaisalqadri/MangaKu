package com.uwaisalqadri.mangaku.di.feature

import com.uwaisalqadri.mangaku.data.repository.MangaDataStore
import com.uwaisalqadri.mangaku.data.source.local.MangaPersistence
import com.uwaisalqadri.mangaku.data.source.local.MangaPersistenceContainer
import com.uwaisalqadri.mangaku.data.source.remote.MangaApi
import com.uwaisalqadri.mangaku.data.source.remote.MangaApiClient
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import com.uwaisalqadri.mangaku.domain.usecase.browse.BrowseUseCase
import com.uwaisalqadri.mangaku.domain.usecase.browse.TrendingUseCase
import com.uwaisalqadri.mangaku.domain.usecase.detail.DetailUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.AddMangaUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.DeleteMangaUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.GetMyMangaByIdUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.GetMyMangaUseCase
import org.koin.dsl.module

val mangaModule = module {
    single<MangaApiClient> { MangaApi(get()) }
    single<MangaPersistence> { MangaPersistenceContainer() }
    single<MangaRepository> { MangaDataStore(get(), get()) }
    single { BrowseUseCase(get()) }
    single { TrendingUseCase(get()) }
    single { DetailUseCase(get()) }
    single { AddMangaUseCase(get()) }
    single { DeleteMangaUseCase(get()) }
    single { GetMyMangaByIdUseCase(get()) }
    single { GetMyMangaUseCase(get()) }
}
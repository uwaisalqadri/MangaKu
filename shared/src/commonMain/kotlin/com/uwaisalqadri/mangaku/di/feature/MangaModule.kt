package com.uwaisalqadri.mangaku.di.feature

import com.uwaisalqadri.mangaku.data.repository.MangaRepositoryImpl
import com.uwaisalqadri.mangaku.data.source.local.MangaLocalDataSource
import com.uwaisalqadri.mangaku.data.source.local.MangaLocalDataSourceImpl
import com.uwaisalqadri.mangaku.data.source.remote.MangaApiDataSourceImpl
import com.uwaisalqadri.mangaku.data.source.remote.MangaApiDataSource
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
    single<MangaApiDataSource> { MangaApiDataSourceImpl(get()) }
    single<MangaLocalDataSource> { MangaLocalDataSourceImpl() }
    single<MangaRepository> { MangaRepositoryImpl(get(), get()) }
    single { BrowseUseCase(get()) }
    single { TrendingUseCase(get()) }
    single { DetailUseCase(get()) }
    single { AddMangaUseCase(get()) }
    single { DeleteMangaUseCase(get()) }
    single { GetMyMangaByIdUseCase(get()) }
    single { GetMyMangaUseCase(get()) }
}
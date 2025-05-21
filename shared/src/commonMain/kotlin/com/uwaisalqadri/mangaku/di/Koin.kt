package com.uwaisalqadri.mangaku.di

import com.uwaisalqadri.mangaku.di.feature.apiModule
import com.uwaisalqadri.mangaku.di.feature.databaseModule
import com.uwaisalqadri.mangaku.di.feature.mangaModule
import com.uwaisalqadri.mangaku.domain.usecase.browse.BrowseUseCase
import com.uwaisalqadri.mangaku.domain.usecase.detail.DetailUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.AddMangaUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.DeleteMangaUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.GetMyMangaByIdUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.GetMyMangaUseCase
import com.uwaisalqadri.mangaku.domain.usecase.search.SearchUseCase
import com.uwaisalqadri.mangaku.utils.ktorEngineModule
import com.uwaisalqadri.mangaku.utils.resourceReaderModule
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}): KoinApplication {
    return startKoin {
        appDeclaration()
        modules(
            apiModule,
            ktorEngineModule(),
            resourceReaderModule(),
            databaseModule,
            mangaModule
        )
    }
}

// Koin utilities for iOS injection

fun KoinApplication.Companion.start(): KoinApplication = initKoin {}

val Koin.browseUseCase: BrowseUseCase
    get() = get()

val Koin.searchUseCase: SearchUseCase
    get() = get()

val Koin.detailUseCase: DetailUseCase
    get() = get()

val Koin.getMyMangaUseCase: GetMyMangaUseCase
    get() = get()

val Koin.getMyMangaByIdUseCase: GetMyMangaByIdUseCase
    get() = get()

val Koin.addMangaUseCase: AddMangaUseCase
    get() = get()

val Koin.deleteMangaUseCase: DeleteMangaUseCase
    get() = get()

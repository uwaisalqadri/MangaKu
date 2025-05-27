package com.uwaisalqadri.mangaku.di

import com.uwaisalqadri.mangaku.data.source.local.dbDriverModule
import com.uwaisalqadri.mangaku.data.source.remote.ktorEngineModule
import com.uwaisalqadri.mangaku.domain.usecase.browse.BrowseUseCase
import com.uwaisalqadri.mangaku.domain.usecase.detail.DetailUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.AddMangaUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.DeleteMangaUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.GetMyMangaByIdUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.GetMyMangaUseCase
import com.uwaisalqadri.mangaku.domain.usecase.search.SearchUseCase
import com.uwaisalqadri.mangaku.utils.resourceReaderModule
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.mp.KoinPlatformTools

fun initKoin(appDeclaration: KoinAppDeclaration = {}): KoinApplication {
    return startKoin {
        appDeclaration()
        modules(
            ktorEngineModule(),
            resourceReaderModule(),
            dbDriverModule(),
            dataModule,
            domainModule
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

// Koin utilities for injection (jvm, iosarm64, iossimulatorarm64, iosx64)

inline fun <reified T : Any> injectLazy(): Lazy<T> {
    return lazy { KoinPlatformTools.defaultContext().get().get(T::class) }
}

inline fun <reified T : Any> injectLazy(key: String): Lazy<T> {
    return lazy { KoinPlatformTools.defaultContext().get().get(T::class, named(key)) }
}

inline fun <reified T : Any> inject(): T {
    return KoinPlatformTools.defaultContext().get().get(T::class)
}

inline fun <reified T : Any> inject(key: String): T {
    return KoinPlatformTools.defaultContext().get().get(T::class, named(key))
}

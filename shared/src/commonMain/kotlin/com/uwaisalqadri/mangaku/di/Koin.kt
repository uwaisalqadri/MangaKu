package com.uwaisalqadri.mangaku.di

import com.uwaisalqadri.mangaku.data.source.db.dbDriverModule
import com.uwaisalqadri.mangaku.data.source.api.ktorEngineModule
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

// Initializes Koin with optional application-specific declarations
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


fun KoinApplication.Companion.start(): KoinApplication = initKoin()


val Koin.browseUseCase get() = get<BrowseUseCase>()
val Koin.searchUseCase get() = get<SearchUseCase>()
val Koin.detailUseCase get() = get<DetailUseCase>()
val Koin.getMyMangaUseCase get() = get<GetMyMangaUseCase>()
val Koin.getMyMangaByIdUseCase get() = get<GetMyMangaByIdUseCase>()
val Koin.addMangaUseCase get() = get<AddMangaUseCase>()
val Koin.deleteMangaUseCase get() = get<DeleteMangaUseCase>()


/**
 * Lazily injects a dependency of type [T]
 */
inline fun <reified T : Any> injectLazy(): Lazy<T> =
    lazy { KoinPlatformTools.defaultContext().get().get<T>() }

/**
 * Lazily injects a dependency of type [T] with a named qualifier
 */
inline fun <reified T : Any> injectLazy(key: String): Lazy<T> =
    lazy { KoinPlatformTools.defaultContext().get().get<T>(named(key)) }

/**
 * Directly injects a dependency of type [T]
 */
inline fun <reified T : Any> inject(): T =
    KoinPlatformTools.defaultContext().get().get<T>()

/**
 * Directly injects a dependency of type [T] with a named qualifier
 */
inline fun <reified T : Any> inject(key: String): T =
    KoinPlatformTools.defaultContext().get().get<T>(named(key))

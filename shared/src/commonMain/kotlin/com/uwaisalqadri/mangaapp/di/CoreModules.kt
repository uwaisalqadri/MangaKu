package com.uwaisalqadri.mangaapp.di

import com.uwaisalqadri.mangaapp.data.repository.MangaRepositoryImpl
import com.uwaisalqadri.mangaapp.data.souce.remote.ApiService
import com.uwaisalqadri.mangaapp.domain.repository.MangaRepository
import com.uwaisalqadri.mangaapp.domain.usecase.manga_detail.GetMangaDetailInteractor
import com.uwaisalqadri.mangaapp.domain.usecase.manga_detail.GetMangaDetailUseCase
import com.uwaisalqadri.mangaapp.domain.usecase.manga_list.GetMangaListInteractor
import com.uwaisalqadri.mangaapp.domain.usecase.manga_list.GetMangaListUseCase
import com.uwaisalqadri.mangaapp.domain.usecase.manga_search.GetMangaSearchInteractor
import com.uwaisalqadri.mangaapp.domain.usecase.manga_search.GetMangaSearchUseCase
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(networkModule, repositoryModule, useCaseModule)
    }

val useCaseModule = module {
    single<GetMangaListUseCase> {
        GetMangaListInteractor(get())
    }

    single<GetMangaSearchUseCase> {
        GetMangaSearchInteractor(get())
    }

    single<GetMangaDetailUseCase> {
        GetMangaDetailInteractor(get())
    }
}

val repositoryModule = module {
    single<MangaRepository> {
        MangaRepositoryImpl(get())
    }
}

val networkModule = module {
    single { ApiService(get()) }
    single { createJson() }
    single { createHttpClient(get(), true) }
}

fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }

fun createHttpClient(json: Json, enableNetworkLogs: Boolean) = HttpClient {
    install(JsonFeature) {
        serializer = KotlinxSerializer(json)
    }

    if (enableNetworkLogs) {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
    }
}
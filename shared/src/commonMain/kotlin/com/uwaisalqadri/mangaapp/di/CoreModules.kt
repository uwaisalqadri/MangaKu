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

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(networkModule, repositoryModule, useCaseModule)
    }

fun initKoin() = initKoin {}

val useCaseModule = module {
    single<GetMangaListUseCase> {
        GetMangaListInteractor()
    }

    single<GetMangaSearchUseCase> {
        GetMangaSearchInteractor()
    }

    single<GetMangaDetailUseCase> {
        GetMangaDetailInteractor()
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
    single { createHttpClient(get()) }
}

fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }

fun createHttpClient(json: Json) = HttpClient {
    install(JsonFeature) {
        serializer = KotlinxSerializer(json)
    }

    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.INFO
    }
}
package com.uwaisalqadri.mangaapp.di

import co.touchlab.kermit.CommonLogger
import co.touchlab.kermit.Kermit
import com.uwaisalqadri.mangaapp.data.mapper.entity.*
import com.uwaisalqadri.mangaapp.data.mapper.response.*
import com.uwaisalqadri.mangaapp.data.mapper.response.CoverImageMapper
import com.uwaisalqadri.mangaapp.data.repository.MangaRepositoryImpl
import com.uwaisalqadri.mangaapp.data.souce.local.LocalDataSource
import com.uwaisalqadri.mangaapp.data.souce.local.entity.MangaObject
import com.uwaisalqadri.mangaapp.data.souce.remote.RemoteDataSource
import com.uwaisalqadri.mangaapp.domain.repository.MangaRepository
import com.uwaisalqadri.mangaapp.domain.usecase.detail.GetMangaDetailInteractor
import com.uwaisalqadri.mangaapp.domain.usecase.detail.GetMangaDetailUseCase
import com.uwaisalqadri.mangaapp.domain.usecase.list.GetMangaListInteractor
import com.uwaisalqadri.mangaapp.domain.usecase.list.GetMangaListUseCase
import com.uwaisalqadri.mangaapp.domain.usecase.list.GetMangaTrendingInteractor
import com.uwaisalqadri.mangaapp.domain.usecase.list.GetMangaTrendingUseCase
import com.uwaisalqadri.mangaapp.domain.usecase.search.GetMangaSearchInteractor
import com.uwaisalqadri.mangaapp.domain.usecase.search.GetMangaSearchUseCase
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(networkModule, realmModule, repositoryModule, useCaseModule, mapperModule)
    }

fun initKoin() = initKoin {} // for iOS

val mapperModule = module {
    single { CoverImageMapper() }
    single { MangaMapper(get()) }
    single { PosterImageMapper() }
    single { TitlesMapper() }
    single { AttributesMapper(get(), get(), get()) }

    single { CoverImageObjectMapper() }
    single { MangaObjectMapper(get()) }
    single { PosterImageObjectMapper() }
    single { TitlesObjectMapper() }
    single { AttributesObjectMapper(get(), get(), get()) }
}

val useCaseModule = module {
    single<GetMangaListUseCase> {
        GetMangaListInteractor(get())
    }

    single<GetMangaTrendingUseCase> {
        GetMangaTrendingInteractor(get())
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
        MangaRepositoryImpl(get(), get(), get(), get())
    }
}

val realmModule = module {
    single { LocalDataSource(get()) }
    single { createRealmDatabase() }
}

val networkModule = module {
    single { RemoteDataSource(get()) }
    single { createJson() }
    single { createHttpClient(get()) }
    single { Kermit(CommonLogger()) }
}

fun createRealmDatabase(): Realm {
    val configuration = RealmConfiguration(schema = setOf(MangaObject::class))
    return Realm(configuration)
}

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

fun createHttpClient(json: Json) = HttpClient {
    install(JsonFeature) {
        serializer = KotlinxSerializer(json)
    }

    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.INFO
    }
}
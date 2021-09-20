package com.uwaisalqadri.mangaku.di

import co.touchlab.kermit.CommonLogger
import co.touchlab.kermit.Kermit
import com.uwaisalqadri.mangaku.data.mapper.entity.*
import com.uwaisalqadri.mangaku.data.mapper.response.*
import com.uwaisalqadri.mangaku.data.mapper.response.CoverImageMapper
import com.uwaisalqadri.mangaku.data.repository.MangaRepositoryImpl
import com.uwaisalqadri.mangaku.data.souce.local.LocalDataSource
import com.uwaisalqadri.mangaku.data.souce.local.entity.*
import com.uwaisalqadri.mangaku.data.souce.remote.RemoteDataSource
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import com.uwaisalqadri.mangaku.domain.usecase.detail.GetMangaDetailInteractor
import com.uwaisalqadri.mangaku.domain.usecase.detail.GetMangaDetailUseCase
import com.uwaisalqadri.mangaku.domain.usecase.browse.*
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.*
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

    single<GetMangaFavoriteUseCase> {
        GetMangaFavoriteInteractor(get())
    }

    single<CreateMangaFavoriteUseCase> {
        CreateMangaFavoriteInteractor(get())
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
    val configuration = RealmConfiguration(schema = setOf(
        MangaObject::class,
        AttributesObject::class,
        CoverImageObject::class,
        PosterImageObject::class,
        TitlesObject::class
    ))

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
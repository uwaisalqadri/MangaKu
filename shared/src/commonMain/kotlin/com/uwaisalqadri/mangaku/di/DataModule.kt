package com.uwaisalqadri.mangaku.di

import com.uwaisalqadri.mangaku.data.repository.MangaRepositoryImpl
import com.uwaisalqadri.mangaku.data.source.remote.NetworkService
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import com.uwaisalqadri.mangaku.utils.YamlResourceReader
import org.koin.dsl.module

val dataModule = module {
    single { NetworkService() }
    single { YamlResourceReader(get()) }
    single<MangaRepository> { MangaRepositoryImpl(get(), get()) }
}

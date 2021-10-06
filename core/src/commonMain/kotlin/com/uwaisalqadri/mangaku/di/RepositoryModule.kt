package com.uwaisalqadri.mangaku.di

import com.uwaisalqadri.mangaku.data.repository.MangaRepositoryImpl
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MangaRepository> {
        MangaRepositoryImpl(get(), get(), get(), get())
    }
}
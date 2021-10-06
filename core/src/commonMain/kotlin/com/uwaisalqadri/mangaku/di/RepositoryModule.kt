package com.uwaisalqadri.mangaku.di

import com.uwaisalqadri.mangaku.data.repository.DefaultMangaRepository
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MangaRepository> {
        DefaultMangaRepository(get(), get(), get(), get())
    }
}
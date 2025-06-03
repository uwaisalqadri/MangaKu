package com.uwaisalqadri.mangaku.di

import com.uwaisalqadri.mangaku.data.repository.MangaRepositoryImpl
import com.uwaisalqadri.mangaku.data.source.api.NetworkService
import com.uwaisalqadri.mangaku.data.source.db.MangaDao
import com.uwaisalqadri.mangaku.data.source.db.MangaDatabase
import com.uwaisalqadri.mangaku.data.source.db.getMangaDao
import com.uwaisalqadri.mangaku.data.source.db.getRoomDatabase
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import com.uwaisalqadri.mangaku.utils.YamlResourceReader
import org.koin.dsl.module

val dataModule = module {
    single { NetworkService() }
    single { YamlResourceReader(get()) }
    single { getRoomDatabase(get()) }
    single<MangaDao> { getMangaDao(get()) }
    single<MangaRepository> { MangaRepositoryImpl(get(), get()) }
}

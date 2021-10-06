package com.uwaisalqadri.mangaku.di

import com.uwaisalqadri.mangaku.data.mapper.entity.*
import com.uwaisalqadri.mangaku.data.mapper.response.*
import org.koin.dsl.module

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
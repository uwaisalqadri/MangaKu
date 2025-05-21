package com.uwaisalqadri.mangaku.di.feature

import com.uwaisalqadri.mangaku.data.source.local.MangaPersistenceContainer
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseModule = module {
    singleOf(::MangaPersistenceContainer)
//    single { createRealmDatabase() }
}

//fun createRealmDatabase(): Realm {
//    val configuration = RealmConfiguration.create(schema = setOf(
//        MangaObject::class,
//        AttributesObject::class,
//        CoverImageObject::class,
//        PosterImageObject::class,
//        TitlesObject::class
//    ))
//
//    return Realm.open(configuration = configuration)
//}
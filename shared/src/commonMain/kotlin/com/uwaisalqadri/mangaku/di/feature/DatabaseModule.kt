package com.uwaisalqadri.mangaku.di.feature

import com.uwaisalqadri.mangaku.data.souce.local.MangaPersistenceContainer
import com.uwaisalqadri.mangaku.data.souce.local.entity.*
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseModule = module {
    singleOf(::MangaPersistenceContainer)
    single { createRealmDatabase() }
}

fun createRealmDatabase(): Realm {
    val configuration = RealmConfiguration.create(schema = setOf(
        MangaObject::class,
        AttributesObject::class,
        CoverImageObject::class,
        PosterImageObject::class,
        TitlesObject::class
    ))

    return Realm.open(configuration = configuration)
}
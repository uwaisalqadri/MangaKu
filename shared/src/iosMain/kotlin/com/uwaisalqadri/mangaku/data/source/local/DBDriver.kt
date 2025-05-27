package com.uwaisalqadri.mangaku.data.source.local

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.uwaisalqadri.mangaku.db.MangakuDB
import org.koin.dsl.module

actual fun dbDriverModule() = module {
    single<MangaDao> {
        val driver = NativeSqliteDriver(MangakuDB.Schema.synchronous(), "mangastorage.db")
        MangaDaoImpl(MangakuDB(driver))
    }
}
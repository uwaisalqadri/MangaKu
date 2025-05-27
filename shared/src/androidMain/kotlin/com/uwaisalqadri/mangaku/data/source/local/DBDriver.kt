package com.uwaisalqadri.mangaku.data.source.local

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.uwaisalqadri.mangaku.db.MangakuDB
import org.koin.dsl.module

actual fun dbDriverModule() = module {
    single<MangaDao> {
        val driver = AndroidSqliteDriver(MangakuDB.Schema.synchronous(), get(), "mangastorage.db")
        MangaDaoImpl(MangakuDB(driver))
    }
}
package com.uwaisalqadri.mangaku.di.feature

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.uwaisalqadri.mangaku.data.source.local.MangaLocalDataSource
import com.uwaisalqadri.mangaku.data.source.local.MangaLocalDataSourceImpl
import com.uwaisalqadri.mangaku.db.MangakuDB
import org.koin.dsl.module

actual val databaseModule = module {
    single<MangaLocalDataSource> {
        val driver = NativeSqliteDriver(MangakuDB.Schema.synchronous(), "mangastorage.db")
        MangaLocalDataSourceImpl(MangakuDB(driver))
    }
}
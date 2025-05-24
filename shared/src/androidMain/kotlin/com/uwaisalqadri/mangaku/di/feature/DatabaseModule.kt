package com.uwaisalqadri.mangaku.di.feature

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.uwaisalqadri.mangaku.data.source.local.MangaLocalDataSource
import com.uwaisalqadri.mangaku.data.source.local.MangaLocalDataSourceImpl
import com.uwaisalqadri.mangaku.db.MangakuDB
import org.koin.dsl.module

actual val databaseModule = module {
    single<MangaLocalDataSource> {
        val driver = AndroidSqliteDriver(MangakuDB.Schema.synchronous(), get(), "mangastorage.db")
        MangaLocalDataSourceImpl(MangakuDB(driver))
    }
}
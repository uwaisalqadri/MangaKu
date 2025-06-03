package com.uwaisalqadri.mangaku.data.source.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.AndroidSQLiteDriver
import org.koin.dsl.module

actual fun dbDriverModule() = module {
    single<RoomDatabase.Builder<MangaDatabase>> { getDatabaseBuilder(get()) }
}

fun getDatabaseBuilder(appContext: Context): RoomDatabase.Builder<MangaDatabase> {
    val dbFile = appContext.getDatabasePath("mangastorage.db")
    return Room.databaseBuilder<MangaDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    ).setDriver(AndroidSQLiteDriver())
}
package com.uwaisalqadri.mangaku.data.source.db

import androidx.room.RoomDatabase
import androidx.room.Room
import androidx.sqlite.SQLiteDriver
import androidx.sqlite.driver.NativeSQLiteDriver
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual fun dbDriverModule() = module {
    single<RoomDatabase.Builder<MangaDatabase>> { getDatabaseBuilder() }
}

fun getDatabaseBuilder(): RoomDatabase.Builder<MangaDatabase> {
    val dbFilePath = documentDirectory() + "/mangastorage.db"
    return Room.databaseBuilder<MangaDatabase>(
        name = dbFilePath
    ).setDriver(NativeSQLiteDriver())
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}
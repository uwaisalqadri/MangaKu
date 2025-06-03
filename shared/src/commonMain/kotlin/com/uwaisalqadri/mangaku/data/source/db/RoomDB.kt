package com.uwaisalqadri.mangaku.data.source.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.uwaisalqadri.mangaku.data.source.db.entity.MangaEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(
    entities = [
        MangaEntity::class
    ],
    version = 1,
    exportSchema = false
)
@ConstructedBy(MangaDatabaseConstructor::class)
abstract class MangaDatabase: RoomDatabase() {
    abstract fun mangaDao(): MangaDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT", "EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object MangaDatabaseConstructor: RoomDatabaseConstructor<MangaDatabase> {
    override fun initialize(): MangaDatabase
}

fun getMangaDao(appDatabase: MangaDatabase) = appDatabase.mangaDao()

fun getRoomDatabase(
    builder: RoomDatabase.Builder<MangaDatabase>
): MangaDatabase {
    return builder
        .addMigrations()
        .fallbackToDestructiveMigrationOnDowngrade(true)
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
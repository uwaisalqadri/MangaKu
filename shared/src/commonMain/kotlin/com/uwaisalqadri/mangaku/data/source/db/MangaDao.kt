package com.uwaisalqadri.mangaku.data.source.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.uwaisalqadri.mangaku.data.source.db.entity.MangaEntity

@Dao
interface MangaDao {
    @Query("SELECT * FROM MangaEntity")
    suspend fun getAllManga(): List<MangaEntity>

    @Query("SELECT * FROM MangaEntity WHERE id = :mangaId")
    suspend fun getMangaById(mangaId: String): List<MangaEntity>

    @Upsert
    suspend fun addManga(manga: MangaEntity)

    @Query("DELETE FROM MangaEntity where id = :mangaId")
    suspend fun deleteManga(mangaId: String)

    @Query("DELETE FROM MangaEntity")
    suspend fun deleteAllManga()
}
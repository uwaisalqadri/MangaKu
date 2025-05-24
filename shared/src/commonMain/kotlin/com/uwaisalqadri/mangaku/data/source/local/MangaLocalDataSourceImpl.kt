package com.uwaisalqadri.mangaku.data.source.local

import com.uwaisalqadri.mangaku.db.MangaEntity
import com.uwaisalqadri.mangaku.db.MangakuDB

class MangaLocalDataSourceImpl(
   private val db: MangakuDB
): MangaLocalDataSource {

    private val queries by lazy { db.mangakuDBQueries }

    override fun getAllManga(): List<MangaEntity> {
        return queries.getAllManga().executeAsList()
    }

    override fun getMangaById(mangaId: String): List<MangaEntity> {
        return queries.getMangaById(mangaId).executeAsList()
    }

    override suspend fun addManga(manga: MangaEntity) {
        queries.transaction {
            queries.addManga(
                id = manga.id,
                type = manga.type,
                ageRating = manga.ageRating,
                ageRatingGuide = manga.ageRatingGuide,
                averageRating = manga.averageRating,
                canonicalTitle = manga.canonicalTitle,
                chapterCount = manga.chapterCount,
                description = manga.description,
                endDate = manga.endDate,
                favoritesCount = manga.favoritesCount,
                mangaType = manga.mangaType,
                nextRelease = manga.nextRelease,
                popularityRank = manga.popularityRank,
                ratingRank = manga.ratingRank,
                serialization = manga.serialization,
                slug = manga.slug,
                startDate = manga.startDate,
                status = manga.status,
                subtype = manga.subtype,
                synopsis = manga.synopsis,
                tba = manga.tba,
                userCount = manga.userCount,
                volumeCount = manga.volumeCount
            )
        }
    }

    override suspend fun deleteManga(mangaId: String) {
        queries.deleteMangaById(mangaId)
    }

    override suspend fun clearAllManga() {
        queries.deleteAllManga()
    }
}
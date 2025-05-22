package com.uwaisalqadri.mangaku.data.source.local

import com.uwaisalqadri.mangaku.data.source.local.entity.MangaObject

class MangaLocalDataSourceImpl(): MangaLocalDataSource {

    override fun getAllManga(): List<MangaObject> {
        return emptyList()
    }

    override fun getMangaById(mangaId: String): List<MangaObject> {
        return emptyList()
    }

    override fun addManga(manga: MangaObject) {
//        db.writeBlocking {
//            copyToRealm(manga)
//        }
    }

    override fun deleteManga(mangaId: String) {
//        db.writeBlocking {
//            val query = query<MangaObject>("id = $0", mangaId).first()
//            delete(query)
//        }
    }

    override fun clearAllManga() {
//        db.writeBlocking {
//            val queries = query<MangaObject>().find()
//            delete(queries)
//        }
    }

    override fun close() {
//        db.close()
    }
}
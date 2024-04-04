package com.uwaisalqadri.mangaku.data.souce.local

import com.uwaisalqadri.mangaku.data.souce.local.entity.MangaObject
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query

class MangaPersistenceContainer(private val db: Realm): MangaPersistence {

    override fun getAllManga(): List<MangaObject> {
        return db.query<MangaObject>().find()
    }

    override fun getMangaById(mangaId: String): List<MangaObject> {
        return db.query<MangaObject>("id = $0", mangaId).find()
    }

    override fun addManga(manga: MangaObject) {
        db.writeBlocking {
            copyToRealm(manga)
        }
    }

    override fun deleteManga(mangaId: String) {
        db.writeBlocking {
            val query = query<MangaObject>("id = $0", mangaId).first()
            delete(query)
        }
    }

    override fun clearAllManga() {
        db.writeBlocking {
            val queries = query<MangaObject>().find()
            delete(queries)
        }
    }

    override fun close() {
        db.close()
    }
}
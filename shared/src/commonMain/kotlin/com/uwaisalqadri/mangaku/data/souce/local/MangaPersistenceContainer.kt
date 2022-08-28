package com.uwaisalqadri.mangaku.data.souce.local

import com.uwaisalqadri.mangaku.data.souce.local.entity.MangaObject
import io.realm.Realm
import io.realm.query

class MangaPersistenceContainer(private val realm: Realm): MangaPersistence {

    override fun getAllManga(): List<MangaObject> {
        return realm.query<MangaObject>().find()
    }

    override fun getMangaById(mangaId: String): List<MangaObject> {
        return realm.query<MangaObject>("id = $0", mangaId).find()
    }

    override fun addManga(manga: MangaObject) {
        realm.writeBlocking {
            copyToRealm(manga)
        }
    }

    override fun deleteManga(mangaId: String) {
        realm.writeBlocking {
            val query = query<MangaObject>("id = $0", mangaId).first()
            delete(query)
        }
    }

    override fun clearAllManga() {
        realm.writeBlocking {
            val queries = query<MangaObject>().find()
            delete(queries)
        }
    }
}
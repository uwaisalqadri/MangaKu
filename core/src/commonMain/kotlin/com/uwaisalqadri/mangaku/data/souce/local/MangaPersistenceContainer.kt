package com.uwaisalqadri.mangaku.data.souce.local

import com.uwaisalqadri.mangaku.data.souce.local.entity.MangaObject
import io.realm.Realm
import io.realm.delete

class MangaPersistenceContainer(private val realm: Realm): MangaPersistence {

    override fun getAllManga(): List<MangaObject> {
        return realm.objects(MangaObject::class)
    }

    override fun getMangaById(mangaId: String): List<MangaObject> {
        return realm.objects(MangaObject::class).query("id = $0", mangaId)
    }

    override fun addManga(manga: MangaObject) {
        realm.writeBlocking {
            copyToRealm(manga)
        }
    }

    override fun deleteManga(mangaId: String) {
        realm.writeBlocking {
            objects(MangaObject::class).query("id = $0", mangaId).first().delete()
        }
    }

    override fun clearAllManga() {
        realm.writeBlocking {
            objects(MangaObject::class).delete()
        }
    }
}
package com.uwaisalqadri.mangaapp.data.souce.local

import com.uwaisalqadri.mangaapp.data.souce.local.entity.MangaObject
import com.uwaisalqadri.mangaapp.domain.model.Manga
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.delete
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val realm: Realm
) {

    fun getAllMangaAsFlowable(): Flow<List<MangaObject>> {
        return realm.objects<MangaObject>().observe()
    }

    fun addManga(manga: MangaObject) {
        realm.writeBlocking {
            copyToRealm(manga)
        }
    }

    fun deleteManga(mangaId: Int) {
        realm.writeBlocking {
            objects(MangaObject::class).query("mangaId = $0", mangaId).first().delete()
        }
    }

    fun clearAllManga() {
        realm.writeBlocking {
            objects(MangaObject::class).delete()
        }
    }
}
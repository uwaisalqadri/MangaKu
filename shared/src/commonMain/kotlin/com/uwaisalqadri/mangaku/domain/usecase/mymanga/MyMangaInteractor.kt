package com.uwaisalqadri.mangaku.domain.usecase.mymanga

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.uwaisalqadri.mangaku.domain.base.execute
import com.uwaisalqadri.mangaku.domain.mapper.map
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

class MyMangaInteractor(private val repository: MangaRepository): MyMangaUseCase {

    @NativeCoroutines
    override suspend fun getMyManga(): Flow<List<Manga>> {
        return execute {
            repository.getFavoriteManga().map()
        }
    }

    @NativeCoroutines
    override suspend fun getMyMangaById(mangaId: String): Flow<List<Manga>> {
        return execute {
            repository.getFavoriteMangaById(mangaId = mangaId).map()
        }
    }

    override fun addManga(manga: Manga) {
        val mapper = manga.map()
        repository.addMangaFavorite(manga = mapper)
    }

    override fun deleteManga(mangaId: String) {
        repository.deleteMangaFavorite(mangaId = mangaId)
    }
}
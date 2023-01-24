package com.uwaisalqadri.mangaku.domain.usecase.detail

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.uwaisalqadri.mangaku.domain.base.execute
import com.uwaisalqadri.mangaku.domain.mapper.map
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

class DetailInteractor(private val repository: MangaRepository): DetailUseCase {

    @NativeCoroutines
    override suspend fun getDetailManga(mangaId: String): Flow<Manga?> {
        return execute {
            repository.getDetailManga(mangaId = mangaId)?.map()
        }
    }
}
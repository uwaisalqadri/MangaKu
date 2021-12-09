package com.uwaisalqadri.mangaku.domain.usecase.detail

import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

class DetailInteractor(private val repository: MangaRepository): DetailUseCase {

    override suspend fun getDetailManga(id: String): Flow<Manga?> {
        return repository.getDetailManga(id = id)
    }
}
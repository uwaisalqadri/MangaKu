package com.uwaisalqadri.mangaku.domain.usecase.detail

import com.uwaisalqadri.mangaku.domain.model.Manga
import kotlinx.coroutines.flow.Flow

interface DetailUseCase {
    suspend fun getDetailManga(mangaId: String): Flow<Manga?>
}
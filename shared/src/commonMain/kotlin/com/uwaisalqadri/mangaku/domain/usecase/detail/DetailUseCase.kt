package com.uwaisalqadri.mangaku.domain.usecase.detail

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.uwaisalqadri.mangaku.domain.model.Manga
import kotlinx.coroutines.flow.Flow

interface DetailUseCase {
    @NativeCoroutines
    suspend fun getDetailManga(mangaId: String): Flow<Manga?>
}
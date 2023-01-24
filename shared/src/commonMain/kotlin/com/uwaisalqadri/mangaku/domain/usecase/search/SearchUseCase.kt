package com.uwaisalqadri.mangaku.domain.usecase.search

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.uwaisalqadri.mangaku.domain.model.Manga
import kotlinx.coroutines.flow.Flow

interface SearchUseCase {
    @NativeCoroutines
    suspend fun getSearchManga(query: String): Flow<List<Manga>>
}
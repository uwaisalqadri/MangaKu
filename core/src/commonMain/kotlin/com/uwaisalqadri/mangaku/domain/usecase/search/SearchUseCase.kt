package com.uwaisalqadri.mangaku.domain.usecase.search

import com.uwaisalqadri.mangaku.domain.model.Manga
import kotlinx.coroutines.flow.Flow

interface SearchUseCase {
    suspend fun getSearchManga(query: String): Flow<List<Manga>>
}
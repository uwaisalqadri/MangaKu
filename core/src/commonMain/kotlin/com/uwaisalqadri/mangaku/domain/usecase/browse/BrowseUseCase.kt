package com.uwaisalqadri.mangaku.domain.usecase.browse

import com.uwaisalqadri.mangaku.domain.model.Manga
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface BrowseUseCase {
    suspend fun getManga(): Flow<List<Manga>>
    suspend fun getTrendingManga(): Flow<List<Manga>>
}
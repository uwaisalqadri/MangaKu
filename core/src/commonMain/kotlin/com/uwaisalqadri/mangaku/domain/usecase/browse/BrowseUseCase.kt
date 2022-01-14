package com.uwaisalqadri.mangaku.domain.usecase.browse

import com.uwaisalqadri.mangaku.domain.model.Manga
import kotlinx.coroutines.flow.Flow

interface BrowseUseCase {
    suspend fun getManga(): List<Manga>
    suspend fun getTrendingManga(): Flow<List<Manga>>
}
package com.uwaisalqadri.mangaku.domain.usecase.search

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.uwaisalqadri.mangaku.domain.base.execute
import com.uwaisalqadri.mangaku.domain.mapper.map
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

class SearchInteractor(private val repository: MangaRepository): SearchUseCase {

    @NativeCoroutines
    override suspend fun getSearchManga(query: String): Flow<List<Manga>> {
        return execute {
            repository.getSearchManga(query = query).map()
        }
    }
}
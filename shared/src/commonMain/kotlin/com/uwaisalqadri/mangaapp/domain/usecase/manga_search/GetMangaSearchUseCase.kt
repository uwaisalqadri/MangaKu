package com.uwaisalqadri.mangaapp.domain.usecase.manga_search

import com.uwaisalqadri.mangaapp.data.souce.remote.response.Manga
import com.uwaisalqadri.mangaapp.domain.repository.MangaRepository
import com.uwaisalqadri.mangaapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface GetMangaSearchUseCase {
    suspend fun execute(query: String): Flow<Resource<List<Manga>>>
}

class GetMangaSearchInteractor: KoinComponent, GetMangaSearchUseCase {
    private val repository: MangaRepository by inject()

    override suspend fun execute(query: String): Flow<Resource<List<Manga>>> {
        return repository.fetchSearchMangas(query)
    }
}
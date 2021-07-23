package com.uwaisalqadri.mangaapp.domain.usecase.manga_search

import com.uwaisalqadri.mangaapp.data.souce.remote.response.Manga
import com.uwaisalqadri.mangaapp.domain.repository.MangaRepository
import com.uwaisalqadri.mangaapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GetMangaSearchUseCase {
    suspend fun execute(query: String): Flow<Resource<List<Manga>>>
}

class GetMangaSearchInteractor(private val repository: MangaRepository): GetMangaSearchUseCase {
    override suspend fun execute(query: String): Flow<Resource<List<Manga>>> {
        return repository.fetchSearchMangas(query)
    }
}
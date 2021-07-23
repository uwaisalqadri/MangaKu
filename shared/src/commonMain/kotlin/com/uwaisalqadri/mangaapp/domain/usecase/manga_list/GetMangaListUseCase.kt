package com.uwaisalqadri.mangaapp.domain.usecase.manga_list

import com.uwaisalqadri.mangaapp.data.souce.remote.response.Manga
import com.uwaisalqadri.mangaapp.domain.repository.MangaRepository
import com.uwaisalqadri.mangaapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GetMangaListUseCase {
    suspend fun execute(): Flow<Resource<List<Manga>>>
}

class GetMangaListInteractor(private val repository: MangaRepository): GetMangaListUseCase {
    override suspend fun execute(): Flow<Resource<List<Manga>>> {
        return repository.fetchMangas()
    }
}
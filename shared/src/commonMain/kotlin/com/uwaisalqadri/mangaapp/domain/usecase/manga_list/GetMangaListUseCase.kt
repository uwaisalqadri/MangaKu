package com.uwaisalqadri.mangaapp.domain.usecase.manga_list

import com.uwaisalqadri.mangaapp.data.souce.remote.response.Manga
import com.uwaisalqadri.mangaapp.domain.repository.MangaRepository
import com.uwaisalqadri.mangaapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface GetMangaListUseCase {
    suspend fun execute(): Flow<Resource<List<Manga>>>
}

class GetMangaListInteractor: KoinComponent, GetMangaListUseCase {
    private val repository: MangaRepository by inject()

    override suspend fun execute(): Flow<Resource<List<Manga>>> {
        return repository.fetchMangas()
    }
}
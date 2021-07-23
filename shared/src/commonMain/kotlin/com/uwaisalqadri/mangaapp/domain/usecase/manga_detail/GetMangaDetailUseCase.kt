package com.uwaisalqadri.mangaapp.domain.usecase.manga_detail

import com.uwaisalqadri.mangaapp.data.souce.remote.response.Manga
import com.uwaisalqadri.mangaapp.domain.repository.MangaRepository
import com.uwaisalqadri.mangaapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GetMangaDetailUseCase {
    suspend fun execute(id: String): Flow<Resource<Manga?>>
}

class GetMangaDetailInteractor(private val repository: MangaRepository): GetMangaDetailUseCase {
    override suspend fun execute(id: String): Flow<Resource<Manga?>> {
        return repository.fetchDetailManga(id)
    }
}
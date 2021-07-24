package com.uwaisalqadri.mangaapp.domain.usecase.manga_detail

import com.uwaisalqadri.mangaapp.data.souce.remote.response.Manga
import com.uwaisalqadri.mangaapp.domain.repository.MangaRepository
import com.uwaisalqadri.mangaapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import org.koin.core.Koin
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface GetMangaDetailUseCase {
    suspend fun execute(id: String): Flow<Resource<Manga?>>
}

class GetMangaDetailInteractor: KoinComponent, GetMangaDetailUseCase {
    private val repository: MangaRepository by inject()

    override suspend fun execute(id: String): Flow<Resource<Manga?>> {
        return repository.fetchDetailManga(id)
    }
}
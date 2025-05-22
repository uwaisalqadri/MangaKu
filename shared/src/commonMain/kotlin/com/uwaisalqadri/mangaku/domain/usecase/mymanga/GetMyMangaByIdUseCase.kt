package com.uwaisalqadri.mangaku.domain.usecase.mymanga

import com.uwaisalqadri.mangaku.domain.base.executing
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import com.uwaisalqadri.mangaku.domain.base.UseCase
import com.uwaisalqadri.mangaku.domain.mapper.map
import kotlinx.coroutines.flow.Flow

class GetMyMangaByIdUseCase(private val repository: MangaRepository) : UseCase<String, List<Manga>> {
    override fun execute(parameter: String): Flow<List<Manga>> {
        return executing {
            repository.getFavoriteMangaById(mangaId = parameter).map()
        }
    }
}
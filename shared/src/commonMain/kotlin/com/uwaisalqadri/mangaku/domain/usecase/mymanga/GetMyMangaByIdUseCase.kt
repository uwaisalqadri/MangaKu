package com.uwaisalqadri.mangaku.domain.usecase.mymanga

import com.uwaisalqadri.mangaku.domain.UseCase
import com.uwaisalqadri.mangaku.domain.executing
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

class GetMyMangaByIdUseCase(private val repository: MangaRepository) :
    UseCase<String, List<Manga>> {
    override fun execute(parameter: String): Flow<List<Manga>> {
        return executing {
            repository.getFavoriteMangaById(mangaId = parameter)
        }
    }
}
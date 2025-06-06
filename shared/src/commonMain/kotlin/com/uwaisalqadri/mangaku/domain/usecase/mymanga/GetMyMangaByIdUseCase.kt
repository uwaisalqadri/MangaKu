package com.uwaisalqadri.mangaku.domain.usecase.mymanga

import com.uwaisalqadri.mangaku.domain.base.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import com.uwaisalqadri.mangaku.domain.utils.UseCase
import com.uwaisalqadri.mangaku.domain.utils.executing
import kotlinx.coroutines.flow.Flow

class GetMyMangaByIdUseCase(private val repository: MangaRepository): UseCase<String, List<Manga>> {
    override fun execute(request: String): Flow<List<Manga>> {
        return executing {
            repository.getFavoriteMangaById(mangaId = request)
        }
    }
}
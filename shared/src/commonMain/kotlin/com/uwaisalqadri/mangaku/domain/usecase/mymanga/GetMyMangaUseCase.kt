package com.uwaisalqadri.mangaku.domain.usecase.mymanga

import com.uwaisalqadri.mangaku.domain.base.UseCase
import com.uwaisalqadri.mangaku.domain.base.executing
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

class GetMyMangaUseCase(private val repository: MangaRepository) : UseCase<Unit, List<Manga>> {
    override fun execute(parameter: Unit): Flow<List<Manga>> {
        return executing {
            repository.getFavoriteManga()
        }
    }
}
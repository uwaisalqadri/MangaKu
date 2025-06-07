package com.uwaisalqadri.mangaku.domain.usecase.browse

import com.uwaisalqadri.mangaku.domain.base.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import com.uwaisalqadri.mangaku.domain.utils.UseCase
import com.uwaisalqadri.mangaku.domain.utils.executing
import kotlinx.coroutines.flow.Flow

class TrendingUseCase(private val repository: MangaRepository): UseCase<Unit, List<Manga>> {
    override fun execute(request: Unit): Flow<List<Manga>> {
        return executing {
            repository.getTrendingManga()
        }
    }
}
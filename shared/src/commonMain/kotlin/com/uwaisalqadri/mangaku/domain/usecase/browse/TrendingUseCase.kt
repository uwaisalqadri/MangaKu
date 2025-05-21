package com.uwaisalqadri.mangaku.domain.usecase.browse

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.uwaisalqadri.mangaku.domain.base.executing
import com.uwaisalqadri.mangaku.domain.mapper.map
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import com.uwaisalqadri.mangaku.domain.usecase.common.UseCase
import kotlinx.coroutines.flow.Flow

class TrendingUseCase(private val repository: MangaRepository): UseCase<Unit, List<Manga>> {
    @NativeCoroutines
    override fun execute(parameter: Unit): Flow<List<Manga>> {
        return executing {
            repository.getTrendingManga().map()
        }
    }
}
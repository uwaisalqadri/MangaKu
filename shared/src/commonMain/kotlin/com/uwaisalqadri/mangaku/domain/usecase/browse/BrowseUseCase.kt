package com.uwaisalqadri.mangaku.domain.usecase.browse

import com.uwaisalqadri.mangaku.domain.UseCase
import com.uwaisalqadri.mangaku.domain.executing
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

class BrowseUseCase(private val repository: MangaRepository): UseCase<Unit, List<Manga>> {
    override fun execute(parameter: Unit): Flow<List<Manga>> {
        return executing {
            repository.getManga()
        }
    }
}
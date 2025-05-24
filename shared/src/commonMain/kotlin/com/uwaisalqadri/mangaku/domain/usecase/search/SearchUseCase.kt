package com.uwaisalqadri.mangaku.domain.usecase.search

import com.uwaisalqadri.mangaku.domain.base.UseCase
import com.uwaisalqadri.mangaku.domain.base.executing
import com.uwaisalqadri.mangaku.data.mapper.map
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

class SearchUseCase(private val repository: MangaRepository) : UseCase<String, List<Manga>> {
    override fun execute(parameter: String): Flow<List<Manga>> {
        return executing {
            repository.getSearchManga(parameter)
        }
    }
}
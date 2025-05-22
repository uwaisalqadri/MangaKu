package com.uwaisalqadri.mangaku.domain.usecase.detail

import com.uwaisalqadri.mangaku.domain.base.executing
import com.uwaisalqadri.mangaku.domain.mapper.map
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import com.uwaisalqadri.mangaku.domain.base.UseCase
import kotlinx.coroutines.flow.Flow

class DetailUseCase(private val repository: MangaRepository): UseCase<String, Manga?> {
    override fun execute(parameter: String): Flow<Manga?> {
        return executing {
            repository.getDetailManga(parameter)?.map()
        }
    }
}
package com.uwaisalqadri.mangaku.domain.usecase.detail

import com.uwaisalqadri.mangaku.domain.base.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import com.uwaisalqadri.mangaku.domain.utils.UseCase
import com.uwaisalqadri.mangaku.domain.utils.executing
import kotlinx.coroutines.flow.Flow

class DetailUseCase(private val repository: MangaRepository): UseCase<String, Manga> {
    override fun execute(request: String): Flow<Manga> {
        return executing {
            repository.getDetailManga(request)
        }
    }
}
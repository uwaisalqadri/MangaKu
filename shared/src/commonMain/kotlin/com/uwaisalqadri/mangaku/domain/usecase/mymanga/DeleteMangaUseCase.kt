package com.uwaisalqadri.mangaku.domain.usecase.mymanga

import com.uwaisalqadri.mangaku.domain.base.executing
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import com.uwaisalqadri.mangaku.domain.base.UseCase
import kotlinx.coroutines.flow.Flow

class DeleteMangaUseCase(private val repository: MangaRepository) : UseCase<String, Unit> {
    override fun execute(parameter: String): Flow<Unit> {
        return executing {
            repository.deleteMangaFavorite(parameter)
        }
    }
}
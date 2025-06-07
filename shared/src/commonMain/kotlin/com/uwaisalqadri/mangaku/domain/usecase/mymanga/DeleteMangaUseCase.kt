package com.uwaisalqadri.mangaku.domain.usecase.mymanga

import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import com.uwaisalqadri.mangaku.domain.utils.UseCase
import com.uwaisalqadri.mangaku.domain.utils.executing
import kotlinx.coroutines.flow.Flow

class DeleteMangaUseCase(private val repository: MangaRepository) : UseCase<String, Unit> {
    override fun execute(request: String): Flow<Unit> {
        return executing {
            repository.deleteMangaFavorite(request)
        }
    }
}
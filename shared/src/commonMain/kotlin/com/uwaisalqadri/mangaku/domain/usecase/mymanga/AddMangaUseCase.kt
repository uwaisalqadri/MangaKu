package com.uwaisalqadri.mangaku.domain.usecase.mymanga

import com.uwaisalqadri.mangaku.domain.base.UseCase
import com.uwaisalqadri.mangaku.domain.base.executing
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

class AddMangaUseCase(private val repository: MangaRepository) : UseCase<Manga, Unit> {
    override fun execute(parameter: Manga): Flow<Unit> {
        return executing {
            repository.addMangaFavorite(parameter)
        }
    }
}
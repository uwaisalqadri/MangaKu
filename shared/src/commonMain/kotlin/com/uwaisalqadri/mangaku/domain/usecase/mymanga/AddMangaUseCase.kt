package com.uwaisalqadri.mangaku.domain.usecase.mymanga

import com.uwaisalqadri.mangaku.domain.base.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import com.uwaisalqadri.mangaku.domain.utils.UseCase
import com.uwaisalqadri.mangaku.domain.utils.executing
import kotlinx.coroutines.flow.Flow

class AddMangaUseCase(private val repository: MangaRepository): UseCase<Manga, Unit> {
    override fun execute(request: Manga): Flow<Unit> {
        return executing {
            repository.addMangaFavorite(request)
        }
    }
}

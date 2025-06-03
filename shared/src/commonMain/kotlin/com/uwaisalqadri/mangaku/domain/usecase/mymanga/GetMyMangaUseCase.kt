package com.uwaisalqadri.mangaku.domain.usecase.mymanga

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.uwaisalqadri.mangaku.domain.utils.UseCase
import com.uwaisalqadri.mangaku.domain.utils.executing
import com.uwaisalqadri.mangaku.domain.base.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

class GetMyMangaUseCase(private val repository: MangaRepository) : UseCase<Unit, List<Manga>> {
    @NativeCoroutines
    override fun execute(request: Unit): Flow<List<Manga>> {
        return executing {
            repository.getFavoriteManga()
        }
    }
}
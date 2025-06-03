package com.uwaisalqadri.mangaku.domain.usecase.mymanga

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.uwaisalqadri.mangaku.domain.utils.UseCase
import com.uwaisalqadri.mangaku.domain.utils.executing
import com.uwaisalqadri.mangaku.domain.base.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

class GetMyMangaByIdUseCase(private val repository: MangaRepository): UseCase<String, List<Manga>> {
    @NativeCoroutines
    override fun execute(request: String): Flow<List<Manga>> {
        return executing {
            repository.getFavoriteMangaById(mangaId = request)
        }
    }
}
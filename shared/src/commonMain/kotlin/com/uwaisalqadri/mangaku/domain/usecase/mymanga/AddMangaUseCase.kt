package com.uwaisalqadri.mangaku.domain.usecase.mymanga

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.uwaisalqadri.mangaku.domain.utils.UseCase
import com.uwaisalqadri.mangaku.domain.utils.executing
import com.uwaisalqadri.mangaku.domain.base.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

class AddMangaUseCase(private val repository: MangaRepository): UseCase<Manga, Unit> {
    @NativeCoroutines
    override fun execute(request: Manga): Flow<Unit> {
        println("ADD MANGA BEFORE EXECUTE ${request.attributes?.slug}")
        return executing {
            println("ADD MANGA AFTER EXECUTE ${request.attributes?.slug}")
            repository.addMangaFavorite(request)
        }
    }
}
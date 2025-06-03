package com.uwaisalqadri.mangaku.domain.usecase.mymanga

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.uwaisalqadri.mangaku.domain.utils.executing
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import com.uwaisalqadri.mangaku.domain.utils.UseCase
import kotlinx.coroutines.flow.Flow

class DeleteMangaUseCase(private val repository: MangaRepository) : UseCase<String, Unit> {
    @NativeCoroutines
    override fun execute(request: String): Flow<Unit> {
        return executing {
            repository.deleteMangaFavorite(request)
        }
    }
}
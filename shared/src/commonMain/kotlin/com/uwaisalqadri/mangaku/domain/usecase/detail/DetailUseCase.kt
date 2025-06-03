package com.uwaisalqadri.mangaku.domain.usecase.detail

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.uwaisalqadri.mangaku.domain.utils.UseCase
import com.uwaisalqadri.mangaku.domain.utils.executing
import com.uwaisalqadri.mangaku.domain.base.model.Manga
import com.uwaisalqadri.mangaku.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

class DetailUseCase(private val repository: MangaRepository): UseCase<String, Manga> {
    @NativeCoroutines
    override fun execute(request: String): Flow<Manga> {
        return executing {
            repository.getDetailManga(request)
        }
    }
}
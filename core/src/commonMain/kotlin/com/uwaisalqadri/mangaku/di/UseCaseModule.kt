package com.uwaisalqadri.mangaku.di

import com.uwaisalqadri.mangaku.domain.usecase.browse.*
import com.uwaisalqadri.mangaku.domain.usecase.detail.GetMangaDetailInteractor
import com.uwaisalqadri.mangaku.domain.usecase.detail.GetMangaDetailUseCase
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.GetMangaFavoriteInteractor
import com.uwaisalqadri.mangaku.domain.usecase.mymanga.GetMangaFavoriteUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single<GetMangaListUseCase> {
        GetMangaListInteractor(get())
    }

    single<GetMangaTrendingUseCase> {
        GetMangaTrendingInteractor(get())
    }

    single<GetMangaSearchUseCase> {
        GetMangaSearchInteractor(get())
    }

    single<GetMangaDetailUseCase> {
        GetMangaDetailInteractor(get())
    }

    single<GetMangaFavoriteUseCase> {
        GetMangaFavoriteInteractor(get())
    }
}
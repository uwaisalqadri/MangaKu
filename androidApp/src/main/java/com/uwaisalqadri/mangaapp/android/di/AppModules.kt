package com.uwaisalqadri.mangaapp.android.di

import com.uwaisalqadri.mangaapp.android.ui.browse.BrowseViewModel
import com.uwaisalqadri.mangaapp.android.ui.mymanga.MyMangaViewModel
import com.uwaisalqadri.mangaapp.android.ui.saved.SavedViewModel
import com.uwaisalqadri.mangaapp.data.repository.MangaRepositoryImpl
import com.uwaisalqadri.mangaapp.domain.repository.MangaRepository
import com.uwaisalqadri.mangaapp.domain.usecase.detail.GetMangaDetailInteractor
import com.uwaisalqadri.mangaapp.domain.usecase.detail.GetMangaDetailUseCase
import com.uwaisalqadri.mangaapp.domain.usecase.list.GetMangaListInteractor
import com.uwaisalqadri.mangaapp.domain.usecase.list.GetMangaListUseCase
import com.uwaisalqadri.mangaapp.domain.usecase.search.GetMangaSearchInteractor
import com.uwaisalqadri.mangaapp.domain.usecase.search.GetMangaSearchUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { BrowseViewModel(get(), get()) }
    viewModel { MyMangaViewModel(get()) }
    viewModel { SavedViewModel(get()) }
}

val useCaseModule = module {
    single<GetMangaListUseCase> {
        GetMangaListInteractor(get())
    }

    single<GetMangaSearchUseCase> {
        GetMangaSearchInteractor(get())
    }

    single<GetMangaDetailUseCase> {
        GetMangaDetailInteractor(get())
    }
}

val repositoryModule = module {
    single<MangaRepository> {
        MangaRepositoryImpl(get())
    }
}
package com.uwaisalqadri.mangaku.android.presentation.mymanga

import com.uwaisalqadri.mangaku.domain.base.model.Manga

sealed class MyMangaAction {
    data object GetMyMangas: MyMangaAction()
    data class CheckFavorite(val mangaId: String): MyMangaAction()
    data class AddFavorite(val manga: Manga): MyMangaAction()
    data class DeleteFavorite(val mangaId: String): MyMangaAction()
}
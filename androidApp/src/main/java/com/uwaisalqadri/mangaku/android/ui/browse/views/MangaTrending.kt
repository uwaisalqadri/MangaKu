package com.uwaisalqadri.mangaku.android.ui.browse.views

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uwaisalqadri.mangaku.android.ui.browse.BrowseViewModel
import com.uwaisalqadri.mangaku.android.ui.mymanga.MyMangaViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun MangaTrending(
    viewModel: BrowseViewModel,
    myMangaViewModel: MyMangaViewModel = getViewModel(),
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        viewModel.trendingManga.value.forEach { manga ->
            Manga(
                manga = manga,
                modifier = Modifier
                    .height(197.dp)
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp)
            ) {
                myMangaViewModel.addFavoriteManga(it)
            }
        }
    }
}
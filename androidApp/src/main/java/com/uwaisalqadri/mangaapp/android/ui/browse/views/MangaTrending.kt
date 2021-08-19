package com.uwaisalqadri.mangaapp.android.ui.browse.views

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uwaisalqadri.mangaapp.android.ui.browse.BrowseViewModel

@Composable
fun MangaTrending(
    viewModel: BrowseViewModel,
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        viewModel.trendingMangas.value.forEach { manga ->
            Manga(
                manga = manga,
                modifier = Modifier
                    .height(197.dp)
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp)
            )
        }
    }
}
package com.uwaisalqadri.mangaapp.android.ui.browse.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uwaisalqadri.mangaapp.android.ui.browse.BrowseViewModel

@Composable
fun MangaTrending(
    paddingValues: PaddingValues,
    viewModel: BrowseViewModel,
    modifier: Modifier
) {
    LazyColumn(
        contentPadding = paddingValues,
        modifier = modifier
    ) {
        items(
            items = viewModel.trendingMangas.value
        ) { manga ->
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
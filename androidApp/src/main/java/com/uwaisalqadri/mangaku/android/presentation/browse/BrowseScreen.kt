package com.uwaisalqadri.mangaku.android.presentation.browse

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uwaisalqadri.mangaku.android.R
import com.uwaisalqadri.mangaku.android.presentation.browse.composables.Genres
import com.uwaisalqadri.mangaku.android.presentation.browse.composables.MangaTrending
import com.uwaisalqadri.mangaku.android.presentation.destinations.DetailScreenDestination
import com.uwaisalqadri.mangaku.android.presentation.destinations.SearchScreenDestination
import com.uwaisalqadri.mangaku.android.presentation.theme.MangaTypography
import com.uwaisalqadri.mangaku.android.presentation.theme.composables.ShimmerBrowseItem
import com.uwaisalqadri.mangaku.android.presentation.theme.composables.TopBar
import org.koin.androidx.compose.getViewModel

@Destination
@Composable
fun BrowseScreen(
    navigator: DestinationsNavigator,
    viewModel: BrowseViewModel = getViewModel(),
) {
    viewModel.getTrendingManga()

    val listManga by viewModel.trendingManga.collectAsState()

    LazyColumn(
        modifier = Modifier
            .background(color = MaterialTheme.colors.primary)
            .fillMaxSize()
    ) {

        item {
            TopBar(
                name = "Browse",
                icon = R.drawable.ic_search
            ) {
                navigator.navigate(SearchScreenDestination())
            }
        }

        item {
            Text(
                text = "Genre",
                style = MangaTypography.h2,
                color = MaterialTheme.colors.secondary,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(start = 20.dp, top = 35.dp)
            )
        }

        item {
            Genres(
                modifier = Modifier
                    .height(130.dp)
                    .fillMaxWidth()
                    .padding(
                        top = 15.dp,
                        bottom = 25.dp,
                        start = 10.dp
                    )
            )
        }

        item {
            Text(
                text = "Trending Now",
                style = MangaTypography.h2,
                fontSize = 15.sp,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.padding(start = 20.dp)
            )
        }

        item {
            if (listManga.loading) {
                repeat(10) {
                    ShimmerBrowseItem()
                }
            }

            MangaTrending(
                trendingManga = listManga.data.orEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 10.dp,
                        bottom = 120.dp
                    )
            ) { mangaId ->
                navigator.navigate(DetailScreenDestination(mangaId = mangaId))
            }
        }

    }
}







package com.uwaisalqadri.mangaku.android.presentation.browse

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.uwaisalqadri.mangaku.android.utils.getValue
import com.uwaisalqadri.mangaku.android.utils.isLoading
import com.uwaisalqadri.mangaku.presentation.BrowseViewModel
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun BrowseScreen(
    navigator: DestinationsNavigator,
) {
    val viewModel: BrowseViewModel = koinViewModel()
    val listMangaState by viewModel.trendingManga.collectAsState()
    var isLoaded by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (!isLoaded) {
            viewModel.getTrendingManga()
            isLoaded = true
        }
    }

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
            if (listMangaState.isLoading()) {
                repeat(10) {
                    ShimmerBrowseItem()
                }
            }

            MangaTrending(
                trendingManga = getValue(listMangaState).orEmpty(),
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







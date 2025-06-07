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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.DetailScreenDestination
import com.ramcosta.composedestinations.generated.destinations.SearchScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uwaisalqadri.mangaku.android.presentation.browse.components.Genres
import com.uwaisalqadri.mangaku.android.presentation.browse.components.MangaTrending
import com.uwaisalqadri.mangaku.android.presentation.theme.MangaTypography
import com.uwaisalqadri.mangaku.android.presentation.theme.R
import com.uwaisalqadri.mangaku.android.presentation.theme.composables.ShimmerBrowseItem
import com.uwaisalqadri.mangaku.android.presentation.theme.composables.TopBar
import org.koin.androidx.compose.koinViewModel

@Destination<RootGraph>
@Composable
fun BrowseScreen(
    navigator: DestinationsNavigator,
) {
    val viewModel: BrowseViewModel = koinViewModel()
    val viewState by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.send(BrowseAction.GetMangas)
    }

    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxSize()
    ) {
        item {
            TopBar(
                name = "Browse",
                icon = R.Drawable.SearchIcon
            ) {
                navigator.navigate(SearchScreenDestination())
            }
        }

        item { SectionHeader(title = "Genre") }

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

        item { SectionHeader(title = "Trending Now") }

        item {
            when {
                viewState.isLoading -> {
                    repeat(10) {
                        ShimmerBrowseItem()
                    }
                }

                viewState.items.isEmpty() -> {
                    Text(
                        text = "No trending manga found.",
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colors.secondary
                    )
                }

                else -> {
                    MangaTrending(
                        trendingManga = viewState.items,
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
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MangaTypography.h2,
        fontSize = 15.sp,
        color = MaterialTheme.colors.secondary,
        modifier = Modifier.padding(start = 20.dp, top = 35.dp)
    )
}








package com.uwaisalqadri.mangaku.android.presentation.browse

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.uwaisalqadri.mangaku.android.R
import com.uwaisalqadri.mangaku.android.presentation.browse.composables.Genres
import com.uwaisalqadri.mangaku.android.presentation.browse.composables.MangaTrending
import com.uwaisalqadri.mangaku.android.presentation.detail.DetailScreen
import com.uwaisalqadri.mangaku.android.presentation.search.SearchScreen
import com.uwaisalqadri.mangaku.android.presentation.theme.MangaTypography
import com.uwaisalqadri.mangaku.android.presentation.theme.composables.ShimmerBrowseItem
import com.uwaisalqadri.mangaku.android.presentation.theme.composables.TopBar
import org.koin.androidx.compose.getViewModel

class BrowseScreen: Screen {

    @Composable
    override fun Content() {
        BrowseContent()
    }

    @Composable
    fun BrowseContent(
        viewModel: BrowseViewModel = getViewModel(),
    ) {
        val listManga by viewModel.trendingManga.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        Column(
            modifier = Modifier
                .verticalScroll(state = rememberScrollState())
                .background(color = MaterialTheme.colors.primary)
        ) {

            TopBar(
                name = "Browse",
                icon = R.drawable.ic_search
            ) {
                navigator.push(SearchScreen(navigator = navigator))
            }

            Spacer(modifier = Modifier.height(35.dp))

            Text(
                text = "Genre",
                style = MangaTypography.h2,
                color = MaterialTheme.colors.secondary,
                fontSize = 15.sp,
                modifier = Modifier.padding(start = 20.dp)
            )

            Genres(
                modifier = Modifier
                    .height(130.dp)
                    .fillMaxWidth()
                    .padding(top = 15.dp, bottom = 25.dp, start = 10.dp)
            )

            Text(
                text = "Trending Now",
                style = MangaTypography.h2,
                fontSize = 15.sp,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.padding(start = 20.dp)
            )

            if (listManga.loading) {
                repeat(10) {
                    ShimmerBrowseItem()
                }
            } else {
                listManga.data?.let {
                    MangaTrending(
                        trendingManga = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 120.dp)
                    ) { mangaId ->
                        navigator.push(DetailScreen(navigator = navigator, mangaId = mangaId))
                    }
                }
            }

        }
    }

}







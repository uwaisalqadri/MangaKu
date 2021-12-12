package com.uwaisalqadri.mangaku.android.ui.browse

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.uwaisalqadri.mangaku.android.ui.browse.composables.Genres
import com.uwaisalqadri.mangaku.android.ui.browse.composables.MangaTrending
import com.uwaisalqadri.mangaku.android.ui.detail.DetailScreen
import com.uwaisalqadri.mangaku.android.ui.search.SearchScreen
import com.uwaisalqadri.mangaku.android.ui.theme.MangaTypography
import com.uwaisalqadri.mangaku.android.ui.theme.composables.ShimmerBrowseItem
import com.uwaisalqadri.mangaku.android.ui.theme.composables.TopBar
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
        val uiState by viewModel.uiState.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
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
                modifier = Modifier.padding(start = 20.dp)
            )

            if (uiState.isLoading) {
                repeat(10) {
                    ShimmerBrowseItem()
                }
            } else {
                MangaTrending(
                    trendingManga = uiState.listManga,
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







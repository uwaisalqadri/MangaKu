package com.uwaisalqadri.mangaku.android.presentation.search

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uwaisalqadri.mangaku.android.presentation.destinations.DetailScreenDestination
import com.uwaisalqadri.mangaku.android.presentation.search.composables.SearchCard
import com.uwaisalqadri.mangaku.android.presentation.search.composables.SearchField
import com.uwaisalqadri.mangaku.android.presentation.search.composables.StaggeredVerticalGrid
import com.uwaisalqadri.mangaku.android.presentation.theme.composables.BackButton
import com.uwaisalqadri.mangaku.android.presentation.theme.composables.ShimmerSearchItem
import com.uwaisalqadri.mangaku.android.presentation.theme.composables.TopBar
import com.uwaisalqadri.mangaku.android.utils.getValue
import com.uwaisalqadri.mangaku.android.utils.isLoading
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator
) {
    val queryHandler: SearchQueryHandler = koinViewModel()
    val searchMangaState by queryHandler.searchViewModel.searchManga.collectAsState()
    var query by queryHandler.query

    BackHandler {
        navigator.popBackStack()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .background(color = MaterialTheme.colors.primary)
            .padding(top = 20.dp, bottom = 20.dp, start = 10.dp, end = 10.dp)
    ) {

        item {
            BackButton { navigator.popBackStack() }
        }

        item {
            TopBar(
                name = "Search",
                modifier = Modifier.padding(top = 20.dp)
            )
        }

        item {
            SearchField(
                query = query,
                placeholder = "Search All Manga..",
                onQueryChanged = queryHandler::onQueryChanged,
                onExecuteSearch = { queryHandler.searchViewModel.getSearchManga(query) },
                onEraseQuery = { query = "" },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            )
        }

        item {
            StaggeredVerticalGrid(maxColumnWidth = 150.dp) {
                if (searchMangaState.isLoading()) {
                    repeat(10) {
                        ShimmerSearchItem()
                    }
                }

                getValue(searchMangaState)?.forEach { manga ->
                    SearchCard(manga = manga) {
                        navigator.navigate(DetailScreenDestination(mangaId = it))
                    }
                }
            }
        }

        item {
            Spacer(
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .height(200.dp)
            )
        }

    }
}











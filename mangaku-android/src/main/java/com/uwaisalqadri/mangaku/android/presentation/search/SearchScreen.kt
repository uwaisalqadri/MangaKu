package com.uwaisalqadri.mangaku.android.presentation.search

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.DetailScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uwaisalqadri.mangaku.android.presentation.search.components.SearchCard
import com.uwaisalqadri.mangaku.android.presentation.search.components.SearchField
import com.uwaisalqadri.mangaku.android.presentation.search.components.StaggeredVerticalGrid
import com.uwaisalqadri.mangaku.android.presentation.theme.MangaTypography
import com.uwaisalqadri.mangaku.android.presentation.theme.composables.BackButton
import com.uwaisalqadri.mangaku.android.presentation.theme.composables.ShimmerSearchItem
import com.uwaisalqadri.mangaku.android.presentation.theme.composables.TopBar
import com.uwaisalqadri.mangaku.domain.base.model.Manga
import org.koin.androidx.compose.koinViewModel

@Destination<RootGraph>
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator
) {
    val viewModel: SearchViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    BackHandler { navigator.popBackStack() }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
            .padding(vertical = 20.dp, horizontal = 10.dp)
    ) {
        item { BackButton { navigator.popBackStack() } }

        item {
            TopBar(
                name = "Search",
                modifier = Modifier.padding(top = 20.dp)
            )
        }

        item {
            SearchField(
                query = state.searchQuery,
                placeholder = "Search All Manga..",
                onQueryChanged = { viewModel.send(SearchAction.GetMangas(it)) },
                onExecuteSearch = { viewModel.send(SearchAction.GetMangas(state.searchQuery)) },
                onEraseQuery = { viewModel.send(SearchAction.Empty) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            )
        }

        item {
            SearchResults(
                isLoading = state.isLoading,
                mangas = state.items,
                onMangaClick = { navigator.navigate(DetailScreenDestination(mangaId = it)) }
            )
        }

        item {
            Spacer(modifier = Modifier.height(200.dp))
        }
    }
}

@Composable
private fun SearchResults(
    isLoading: Boolean,
    mangas: List<Manga>,
    onMangaClick: (String) -> Unit
) {
    StaggeredVerticalGrid(maxColumnWidth = 150.dp) {
        when {
            isLoading -> repeat(10) { ShimmerSearchItem() }

            mangas.isEmpty() -> Text(
                text = "No results found.",
                style = MangaTypography.h2,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.padding(20.dp)
            )

            else -> mangas.forEach { manga ->
                SearchCard(manga = manga) {
                    onMangaClick(manga.id)
                }
            }
        }
    }
}
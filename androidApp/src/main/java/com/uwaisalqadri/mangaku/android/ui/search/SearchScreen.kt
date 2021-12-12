package com.uwaisalqadri.mangaku.android.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import com.uwaisalqadri.mangaku.android.ui.detail.DetailScreen
import com.uwaisalqadri.mangaku.android.ui.search.composables.SearchField
import com.uwaisalqadri.mangaku.android.ui.search.composables.SearchResult
import com.uwaisalqadri.mangaku.android.ui.search.composables.StaggeredVerticalGrid
import com.uwaisalqadri.mangaku.android.ui.theme.composables.BackButton
import com.uwaisalqadri.mangaku.android.ui.theme.composables.ShimmerSearchItem
import com.uwaisalqadri.mangaku.android.ui.theme.composables.TopBar
import org.koin.androidx.compose.getViewModel

class SearchScreen(val navigator: Navigator): Screen {

    @Composable
    override fun Content() {
        SearchContent()
    }

    @Composable
    fun SearchContent(
        viewModel: SearchViewModel = getViewModel()
    ) {
        val uiState by viewModel.uiState.collectAsState()
        val query = viewModel.query.value

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(color = MaterialTheme.colors.primary)
        ) {

            BackButton(
                modifier = Modifier.padding(start = 25.dp, top = 25.dp)
            ) {
                navigator.pop()
            }

            Spacer(modifier = Modifier.padding(top = 20.dp))

            TopBar(name = "Search")

            SearchField(
                query = query,
                placeholder = "Search All Manga..",
                onQueryChanged = viewModel::onQueryChanged,
                onExecuteSearch = { viewModel.getSearchManga(query) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            )

            StaggeredVerticalGrid(
                maxColumnWidth = 150.dp
            ) {
                if (uiState.isLoading) {
                    repeat(10) {
                        ShimmerSearchItem()
                    }
                } else {
                    uiState.listManga.forEach { manga ->
                        SearchResult(manga = manga) {
                            navigator.push(DetailScreen(navigator = navigator, mangaId = it))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier
                .background(color = MaterialTheme.colors.primary)
                .height(200.dp)
            )

        }
    }
}












package com.uwaisalqadri.mangaku.android.presentation.mymanga

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.DetailScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uwaisalqadri.mangaku.android.presentation.mymanga.components.HorizontalPagerWithTransition
import com.uwaisalqadri.mangaku.android.presentation.mymanga.components.LayoutSwitch
import com.uwaisalqadri.mangaku.android.presentation.mymanga.components.MyMangaGridItem
import com.uwaisalqadri.mangaku.android.presentation.search.components.StaggeredVerticalGrid
import com.uwaisalqadri.mangaku.android.presentation.theme.MangaTypography
import com.uwaisalqadri.mangaku.domain.base.model.Manga
import org.koin.androidx.compose.koinViewModel

@Destination<RootGraph>
@Composable
fun MyMangaScreen(
    navigator: DestinationsNavigator
) {
    val viewModel: MyMangaViewModel = koinViewModel()
    val viewState by viewModel.state.collectAsState()

    var isPageLayout by rememberSaveable { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.send(MyMangaAction.GetMyMangas)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .background(MaterialTheme.colors.primary)
    ) {
        item {
            TitleBar()
        }

        item {
            LayoutSwitch(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp)
            ) {
                isPageLayout = it
            }
        }

        item {
            MangaContent(
                viewState = viewState,
                isPageLayout = isPageLayout,
                onItemClick = { manga ->
                    navigator.navigate(DetailScreenDestination(mangaId = manga.id))
                }
            )
        }
    }
}

@Composable
private fun TitleBar() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        Text(
            text = "My Manga",
            style = MangaTypography.h1,
            fontSize = 25.sp,
            color = MaterialTheme.colors.secondary
        )
    }
}

@Composable
private fun MangaContent(
    viewState: MyMangaState,
    isPageLayout: Boolean,
    onItemClick: (Manga) -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        when {
            viewState.isLoading || viewState.items.isEmpty() -> {
                Text(
                    text = "Still Empty Here!",
                    style = MangaTypography.overline,
                    fontSize = 60.sp,
                    color = MaterialTheme.colors.secondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 40.dp)
                )
            }

            isPageLayout -> {
                HorizontalPagerWithTransition(
                    manga = viewState.items,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp)
                )
            }

            else -> {
                StaggeredVerticalGrid(
                    maxColumnWidth = 200.dp,
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 30.dp)
                ) {
                    viewState.items.forEach { manga ->
                        MyMangaGridItem(manga = manga) {
                            onItemClick(manga)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(200.dp))
            }
        }
    }
}

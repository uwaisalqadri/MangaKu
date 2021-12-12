package com.uwaisalqadri.mangaku.android.ui.mymanga

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.uwaisalqadri.mangaku.android.ui.detail.DetailScreen
import com.uwaisalqadri.mangaku.android.ui.mymanga.composables.HorizontalPagerWithTransition
import com.uwaisalqadri.mangaku.android.ui.mymanga.composables.LayoutSwitch
import com.uwaisalqadri.mangaku.android.ui.mymanga.composables.MyMangaGridItem
import com.uwaisalqadri.mangaku.android.ui.search.composables.StaggeredVerticalGrid
import com.uwaisalqadri.mangaku.android.ui.theme.MangaTypography
import org.koin.androidx.compose.getViewModel

class MyMangaScreen: Screen {

    @Composable
    override fun Content() {
        MyMangaContent()
    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun MyMangaContent(
        viewModel: MyMangaViewModel = getViewModel()
    ) {
        val uiState by viewModel.uiState.collectAsState()
        var state by remember { mutableStateOf(true) }
        val navigator = LocalNavigator.currentOrThrow

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(color = MaterialTheme.colors.primary)
        ) {

            viewModel.getMyManga()

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

            LayoutSwitch(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp)
            ) {
                state = it
            }

            if (uiState.isLoading) {
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
            } else {
                when(state) {
                    true -> {
                        HorizontalPagerWithTransition(
                            manga = uiState.listManga,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(500.dp)
                        )
                    }

                    false -> {
                        StaggeredVerticalGrid(
                            maxColumnWidth = 200.dp,
                            modifier = Modifier
                                .padding(horizontal = 20.dp, vertical = 30.dp)
                        ) {
                            uiState.listManga.forEach {
                                MyMangaGridItem(manga = it) { manga ->
                                    navigator.push(
                                        DetailScreen(navigator = navigator, mangaId = manga.id)
                                    )
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
        }

    }
}










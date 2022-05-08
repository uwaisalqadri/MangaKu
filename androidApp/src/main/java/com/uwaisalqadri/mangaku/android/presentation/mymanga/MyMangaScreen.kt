package com.uwaisalqadri.mangaku.android.presentation.mymanga

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uwaisalqadri.mangaku.android.presentation.destinations.DetailScreenDestination
import com.uwaisalqadri.mangaku.android.presentation.mymanga.composables.HorizontalPagerWithTransition
import com.uwaisalqadri.mangaku.android.presentation.mymanga.composables.LayoutSwitch
import com.uwaisalqadri.mangaku.android.presentation.mymanga.composables.MyMangaGridItem
import com.uwaisalqadri.mangaku.android.presentation.search.composables.StaggeredVerticalGrid
import com.uwaisalqadri.mangaku.android.presentation.theme.MangaTypography
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalPagerApi::class)
@Destination
@Composable
fun MyMangaScreen(
    navigator: DestinationsNavigator,
    viewModel: MyMangaViewModel = getViewModel()
) {
    val myManga by viewModel.myManga.collectAsState()
    var state by remember { mutableStateOf(true) }

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .background(color = MaterialTheme.colors.primary)
    ) {

        viewModel.getMyManga()

        item {
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

        item {
            LayoutSwitch(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(
                        top = 20.dp,
                        start = 20.dp,
                        end = 20.dp
                    )
            ) {
                state = it
            }
        }

        item {
            if (myManga.loading) {
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
                when (state) {
                    true -> {
                        myManga.data?.let {
                            HorizontalPagerWithTransition(
                                manga = it,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(500.dp)
                            )
                        }
                    }

                    false -> {
                        StaggeredVerticalGrid(
                            maxColumnWidth = 200.dp,
                            modifier = Modifier
                                .padding(horizontal = 20.dp, vertical = 30.dp)
                        ) {
                            myManga.data?.forEach {
                                MyMangaGridItem(manga = it) { manga ->
                                    navigator.navigate(DetailScreenDestination(mangaId = manga.id))
                                }
                            }
                        }

                        Spacer(
                            modifier = Modifier
                                .background(color = Color.Transparent)
                                .height(200.dp)
                        )
                    }
                }

            }
        }
    }

}










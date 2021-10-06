package com.uwaisalqadri.mangaku.android.ui.mymanga

import android.os.Bundle
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.uwaisalqadri.mangaku.android.R
import com.uwaisalqadri.mangaku.android.ui.composables.ShimmerSearchItem
import com.uwaisalqadri.mangaku.android.ui.composables.TopBar
import com.uwaisalqadri.mangaku.android.ui.detail.DetailFragment
import com.uwaisalqadri.mangaku.android.ui.mymanga.composables.HorizontalPagerWithTransition
import com.uwaisalqadri.mangaku.android.ui.mymanga.composables.LayoutSwitch
import com.uwaisalqadri.mangaku.android.ui.mymanga.composables.MyMangaGridItem
import com.uwaisalqadri.mangaku.android.ui.search.composables.SearchResult
import com.uwaisalqadri.mangaku.android.ui.search.composables.StaggeredVerticalGrid
import com.uwaisalqadri.mangaku.android.ui.theme.MangaTypography
import org.koin.androidx.compose.getViewModel

class MyMangaFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MyMangaScreen()
            }
        }
    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun MyMangaScreen(
        viewModel: MyMangaViewModel = getViewModel()
    ) {
        val uiState by viewModel.uiState.collectAsState()
        var state by remember { mutableStateOf(true) }

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {

            viewModel.fetchFavoriteManga()

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                Text(
                    text = "My Manga",
                    style = MangaTypography.h1,
                    fontSize = 25.sp
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

            if (uiState.loading) {
                Text(
                    text = "Still Empty Here!",
                    style = MangaTypography.overline,
                    fontSize = 60.sp,
                    color = Color.Black,
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
                                    val bundle = Bundle().apply { putString(DetailFragment.DETAIL, manga.id) }
                                    findNavController().navigate(R.id.action_navigation_mymanga_to_detailFragment, bundle)
                                }
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(200.dp))
                    }
                }


            }
        }

    }
}










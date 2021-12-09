package com.uwaisalqadri.mangaku.android.ui.browse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialFadeThrough
import com.uwaisalqadri.mangaku.android.R
import com.uwaisalqadri.mangaku.android.ui.browse.composables.Genres
import com.uwaisalqadri.mangaku.android.ui.browse.composables.MangaTrending
import com.uwaisalqadri.mangaku.android.ui.composables.ShimmerBrowseItem
import com.uwaisalqadri.mangaku.android.ui.composables.TopBar
import com.uwaisalqadri.mangaku.android.ui.detail.DetailFragment
import com.uwaisalqadri.mangaku.android.ui.mymanga.MyMangaViewModel
import com.uwaisalqadri.mangaku.android.ui.theme.MangaTheme
import com.uwaisalqadri.mangaku.android.ui.theme.MangaTypography
import org.koin.androidx.compose.getViewModel

class BrowseFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                BrowseScreen()
            }
        }
    }

    @Composable
    fun BrowseScreen(
        viewModel: BrowseViewModel = getViewModel(),
    ) {
        val uiState by viewModel.uiState.collectAsState()

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {

            TopBar(
                name = "Browse",
                icon = R.drawable.ic_search
            ) {
                findNavController().navigate(R.id.action_navigation_browse_to_searchFragment)
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

            if (uiState.loading) {
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
                    val bundle = Bundle().apply { putString(DetailFragment.DETAIL, mangaId) }
                    findNavController().navigate(R.id.action_navigation_browse_to_detailFragment, bundle)
                }
            }

        }
    }
}







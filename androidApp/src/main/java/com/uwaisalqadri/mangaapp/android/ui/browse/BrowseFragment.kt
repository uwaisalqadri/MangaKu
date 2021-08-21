package com.uwaisalqadri.mangaapp.android.ui.browse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.uwaisalqadri.mangaapp.android.R
import com.uwaisalqadri.mangaapp.android.ui.browse.views.Genres
import com.uwaisalqadri.mangaapp.android.ui.browse.views.MangaTrending
import com.uwaisalqadri.mangaapp.android.ui.components.TopBar
import com.uwaisalqadri.mangaapp.android.ui.theme.MangaTypography
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
        viewModel: BrowseViewModel = getViewModel()
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {

            TopBar(
                name = "Browse",
                icon = R.drawable.ic_search
            )

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
                    .padding(top = 15.dp, bottom = 25.dp)
            )

            Text(
                text = "Trending Now",
                style = MangaTypography.h2,
                fontSize = 15.sp,
                modifier = Modifier.padding(start = 20.dp)
            )

            MangaTrending(
                viewModel = viewModel,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 120.dp)
            )
        }
    }
}







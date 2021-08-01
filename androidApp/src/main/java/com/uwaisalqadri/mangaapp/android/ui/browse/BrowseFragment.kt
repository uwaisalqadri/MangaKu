package com.uwaisalqadri.mangaapp.android.ui.browse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.uwaisalqadri.mangaapp.android.ui.browse.composables.Manga
import com.uwaisalqadri.mangaapp.android.ui.browse.composables.MangaTrending
import com.uwaisalqadri.mangaapp.android.ui.theme.MangaTheme
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
        paddingValue: PaddingValues = PaddingValues(),
        viewModel: BrowseViewModel = getViewModel()
    ) {
        Column {
            Text(
                text = "Trending Now",
                style = MangaTypography.h2,
                fontSize = 15.sp,
                modifier = Modifier.padding(start = 20.dp)
            )

            MangaTrending(
                paddingValues = paddingValue,
                viewModel = viewModel,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(20.dp, 10.dp)
            )
        }
    }
}







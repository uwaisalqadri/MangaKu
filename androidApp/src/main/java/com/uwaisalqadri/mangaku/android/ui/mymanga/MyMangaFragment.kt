package com.uwaisalqadri.mangaku.android.ui.mymanga

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import co.touchlab.kermit.CommonLogger
import co.touchlab.kermit.Kermit
import com.google.accompanist.pager.ExperimentalPagerApi
import com.uwaisalqadri.mangaku.android.ui.composables.TopBar
import com.uwaisalqadri.mangaku.android.ui.detail.DetailScreen
import com.uwaisalqadri.mangaku.android.ui.mymanga.composables.HorizontalPagerWithTransition
import com.uwaisalqadri.mangaku.utils.getTitle
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

        Column {

            TopBar(name = "My Manga")

            if (uiState.loading) {
                Text(text = "Loading...")
            } else {
                HorizontalPagerWithTransition(manga = uiState.listManga)
            }
        }
    }


}
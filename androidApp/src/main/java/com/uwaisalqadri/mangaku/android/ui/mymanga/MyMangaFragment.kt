package com.uwaisalqadri.mangaku.android.ui.mymanga

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.google.accompanist.pager.ExperimentalPagerApi
import com.uwaisalqadri.mangaku.android.ui.composables.TopBar
import com.uwaisalqadri.mangaku.android.ui.detail.DetailScreen
import com.uwaisalqadri.mangaku.android.ui.mymanga.composables.HorizontalPagerWithTransition
import com.uwaisalqadri.mangaku.utils.getTitle
import org.koin.androidx.compose.getViewModel

class MyMangaFragment: Fragment() {

    @ExperimentalPagerApi
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
    
    @ExperimentalPagerApi
    @Composable
    fun MyMangaScreen(
        viewModel: MyMangaViewModel = getViewModel()
    ) {
        val uiState by viewModel.uiState.collectAsState()
        var index = 0

        Column {

            TopBar(name = uiState.listManga[index].getTitle())

            if (uiState.loading) {
                Text(text = "Loading...")
            } else {
                HorizontalPagerWithTransition(
                    manga = uiState.listManga
                ) { index = it }
            }
        }
    }


}
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
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.uwaisalqadri.mangaku.android.ui.composables.TopBar
import com.uwaisalqadri.mangaku.android.utils.getTitle
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
    
    @Composable
    fun MyMangaScreen(
        viewModel: MyMangaViewModel = getViewModel()
    ) {
        val uiState by viewModel.uiState.collectAsState()

        Column {

            TopBar(name = "MyManga")

            if (uiState.loading) {
                Text(text = "Loading...")
            } else {
                uiState.listManga.forEach { manga ->
                    Text(text = manga.getTitle())
                }
            }
        }
    }


}
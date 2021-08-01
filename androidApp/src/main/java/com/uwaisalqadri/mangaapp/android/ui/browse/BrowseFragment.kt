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
import androidx.fragment.app.Fragment
import com.uwaisalqadri.mangaapp.android.ui.browse.composables.Manga
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
        LazyColumn(
            contentPadding = paddingValue,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(20.dp, 10.dp)
        ) {
            items(
                items = viewModel.mangas.value
            ) { manga ->
                Manga(
                    manga = manga,
                    modifier = Modifier
                        .height(197.dp)
                        .fillMaxWidth()
                        .padding(0.dp, 10.dp)
                )
            }
        }
    }
}
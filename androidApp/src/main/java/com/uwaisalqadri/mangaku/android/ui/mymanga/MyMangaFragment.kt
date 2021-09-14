package com.uwaisalqadri.mangaku.android.ui.mymanga

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.uwaisalqadri.mangaku.android.ui.components.TopBar
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
        Column {
            if (viewModel.isLoaded.value) {
                viewModel.favoriteManga.value.forEach { manga ->
                    Text(text = manga.getTitle())
                }
            }
        }
    }


}
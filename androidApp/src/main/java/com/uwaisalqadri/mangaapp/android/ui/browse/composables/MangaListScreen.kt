package com.uwaisalqadri.mangaapp.android.ui.browse.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.uwaisalqadri.mangaapp.android.ui.browse.BrowseViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun MangaListScreen(
    paddingValue: PaddingValues = PaddingValues(),
    viewModel: BrowseViewModel = getViewModel()
) {
    LazyColumn(
        contentPadding = paddingValue,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        items(
            items = viewModel.mangas.value
        ) { manga ->
            Text(text = manga.attributes.titles.ja_jp)
        }
    }
}








package com.uwaisalqadri.mangaku.android.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.uwaisalqadri.mangaku.android.ui.composables.TopBar
import com.uwaisalqadri.mangaku.android.ui.search.composables.SearchField
import com.uwaisalqadri.mangaku.android.utils.getTitle
import org.koin.androidx.compose.getViewModel

class SearchFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                SearchScreen()
            }
        }
    }

    @Composable
    fun SearchScreen(
        viewModel: SearchViewModel = getViewModel()
    ) {
        val uiState by viewModel.uiState.collectAsState()
        val query = viewModel.query.value

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {

            TopBar(name = "Search")

            SearchField(
                query = query,
                onQueryChanged = viewModel::onQueryChanged,
                onExecuteSearch = { viewModel.fetchSearchManga(query) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            )

            if (uiState.loading) {
                Text(text = "Loading...")
            } else {
                uiState.listManga.forEach {
                    Text(text = (it.getTitle()))
                }
            }
        }
    }
}












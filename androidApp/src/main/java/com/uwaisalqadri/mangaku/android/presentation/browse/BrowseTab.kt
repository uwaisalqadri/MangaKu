package com.uwaisalqadri.mangaku.android.presentation.browse

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.ScaleTransition
import cafe.adriel.voyager.transitions.SlideTransition
import com.uwaisalqadri.mangaku.android.R

object BrowseTab: Tab {

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_browse_active))

            return remember {
                TabOptions(
                    index = 1u,
                    title = "Browse",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        BrowseTabContent()
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun BrowseTabContent() {
        Navigator(
            screen = BrowseScreen()
        ) {
            SlideTransition(navigator = it)
        }
    }
}
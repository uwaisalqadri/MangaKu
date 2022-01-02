package com.uwaisalqadri.mangaku.android.presentation.mymanga

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import com.uwaisalqadri.mangaku.android.R

object MyMangaTab: Tab {

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_mymanga_active))

            return remember {
                TabOptions(
                    index = 0u,
                    title = "MyManga",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        MyMangaTabContent()
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun MyMangaTabContent() {
        Navigator(
            screen = MyMangaScreen()
        ) {
            SlideTransition(navigator = it)
        }
    }
}
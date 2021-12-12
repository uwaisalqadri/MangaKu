package com.uwaisalqadri.mangaku.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.uwaisalqadri.mangaku.android.ui.browse.BrowseTab
import com.uwaisalqadri.mangaku.android.ui.mymanga.MyMangaTab
import com.uwaisalqadri.mangaku.android.ui.theme.composables.MangaBottomNavigation

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Content()
        }
    }

    @Composable
    fun Content() {
        TabNavigator(tab = BrowseTab) {
            Scaffold(
                content = {
                    CurrentTab()
                },

                bottomBar = {
                    MangaBottomNavigation {
                        TabNavigationItem(tab = BrowseTab)
                        TabNavigationItem(tab = MyMangaTab)
                    }
                }
            )
        }
    }


    @Composable
    private fun RowScope.TabNavigationItem(tab: Tab) {
        val tabNavigator = LocalTabNavigator.current

        BottomNavigationItem(
            selected = tabNavigator.current.key == tab.key,
            onClick = { tabNavigator.current = tab },
            icon = { tab.options.icon?.let { Icon(painter = it, contentDescription = tab.options.title) } }
        )
    }

}

package com.uwaisalqadri.mangaku.android.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uwaisalqadri.mangaku.android.presentation.browse.BrowseScreen
import com.uwaisalqadri.mangaku.android.presentation.mymanga.MyMangaScreen
import com.uwaisalqadri.mangaku.android.presentation.theme.MangaTheme
import com.uwaisalqadri.mangaku.android.presentation.theme.composables.BottomNavItem
import com.uwaisalqadri.mangaku.android.presentation.theme.composables.MangaBottomNavigation

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MangaTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }

    }
}

@RootNavGraph(start = true)
@Destination
@Composable
fun MainScreen(
    navigator: DestinationsNavigator
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { MangaBottomNavigation(navController) }
    ) {
        NavHost(navController, startDestination = BottomNavItem.Browse.route) {
            composable(route = BottomNavItem.Browse.route) {
                BrowseScreen(navigator)
            }
            composable(route = BottomNavItem.MyManga.route) {
                MyMangaScreen(navigator)
            }
        }
    }
}

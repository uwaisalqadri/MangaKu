package com.uwaisalqadri.mangaku.android.presentation.theme.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.uwaisalqadri.mangaku.android.R

sealed class BottomNavItem(
    var route: String,
    @DrawableRes var icon: Int,
    var title: String
) {
    object Browse : BottomNavItem(
        "browse",
        R.drawable.ic_browse_active,
        "Browse"
    )

    object MyManga : BottomNavItem(
        "mymanga",
        R.drawable.ic_mymanga_active,
        "MyManga"
    )
}

@Composable
fun MangaBottomNavigation(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        BottomNavItem.Browse,
        BottomNavItem.MyManga
    )

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 5.dp,
        modifier = modifier
            .padding(horizontal = 80.dp, vertical = 40.dp)
            .height(80.dp)
    ) {
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.primaryVariant,
            contentColor = MaterialTheme.colors.secondary,
            elevation = 10.dp
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            items.forEach { item ->

                BottomNavigationItem(
                    icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                    selectedContentColor = MaterialTheme.colors.secondary,
                    unselectedContentColor = MaterialTheme.colors.secondary.copy(0.4f),
                    alwaysShowLabel = false,
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                )

            }
        }
    }
}












package com.uwaisalqadri.mangaku.android.presentation.theme.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uwaisalqadri.mangaku.android.R
import com.ramcosta.composedestinations.generated.destinations.BrowseScreenDestination
import com.ramcosta.composedestinations.generated.destinations.MyMangaScreenDestination

enum class BottomBarDestination(
    var route: String,
    @DrawableRes var icon: Int,
    var title: String
) {
    BROWSE(
        route = BrowseScreenDestination.route,
        icon = R.drawable.ic_browse_active,
        title = "Browse"
    ),

    MY_MANGA(
        route = MyMangaScreenDestination.route,
        icon = R.drawable.ic_mymanga_active,
        title = "MyManga"
    )
}

@Composable
fun MangakuBottomBar(
    modifier: Modifier = Modifier,
    currentRoute: String?,
    onSelect: (String) -> Unit
) {
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
            BottomBarDestination.values().forEach { item ->

                BottomNavigationItem(
                    icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                    selectedContentColor = MaterialTheme.colors.secondary,
                    unselectedContentColor = MaterialTheme.colors.secondary.copy(0.4f),
                    alwaysShowLabel = false,
                    selected = currentRoute == item.route,
                    onClick = { onSelect(item.route) }
                )

            }
        }
    }
}
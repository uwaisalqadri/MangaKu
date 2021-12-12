package com.uwaisalqadri.mangaku.android.ui.theme.composables

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MangaBottomNavigation(
    content: @Composable RowScope.() -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 5.dp,
        modifier = Modifier
            .padding(horizontal = 80.dp, vertical = 40.dp)
            .height(80.dp)
    ) {
        BottomNavigation(
            backgroundColor = Color.White,
            contentColor = Color.Black,
            elevation = 10.dp,
            content = content,
            modifier = Modifier
                .fillMaxHeight()
        )
    }
}
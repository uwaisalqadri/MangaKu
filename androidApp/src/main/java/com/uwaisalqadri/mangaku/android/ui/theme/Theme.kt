package com.uwaisalqadri.mangaku.android.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun MangaTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = MangaColors,
        typography = MangaTypography,
        shapes = MangaShapes,
        content = content
    )
}
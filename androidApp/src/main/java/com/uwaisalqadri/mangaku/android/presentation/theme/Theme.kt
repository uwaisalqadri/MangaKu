package com.uwaisalqadri.mangaku.android.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun MangaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = MangaLightColors,
        typography = MangaTypography,
        shapes = MangaShapes,
        content = content
    )
}
package com.uwaisalqadri.mangaku.android.presentation.browse.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.uwaisalqadri.mangaku.android.R
import com.uwaisalqadri.mangaku.android.presentation.theme.MangaTypography

@Composable
fun Genres(
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
    ) {
        items(genres) { genre ->
            Genre(
                genre = genre,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .width(135.dp)
                    .fillMaxHeight()
            )
        }
    }
}

@Composable
fun Genre(
    modifier: Modifier = Modifier,
    genre: Genre
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 3.dp,
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = genre.image),
                contentDescription = "Manga Genre",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
            )

            Spacer(
                modifier = Modifier
                    .background(Color.White.copy(alpha = 0.5f))
                    .fillMaxSize()
            )

            Text(
                text = genre.name,
                style = genre.font,
                color = Color.Black
            )
        }
    }
}

data class Genre(
    val name: String,
    @DrawableRes val image: Int,
    val query: String,
    val font: TextStyle
)

val genres = arrayListOf(
    Genre("Shonen", R.drawable.img_shonen, "shonen", MangaTypography.overline),
    Genre("Seinen", R.drawable.img_seinen, "seinen", MangaTypography.caption),
    Genre("Shojo", R.drawable.img_shojo, "shojo", MangaTypography.overline)
)
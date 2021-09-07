package com.uwaisalqadri.mangaku.android.ui.browse.views

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uwaisalqadri.mangaku.android.R
import com.uwaisalqadri.mangaku.android.ui.theme.MangaTypography

@Composable
fun Genres(
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
    ) {
        items(
            items = genres
        ) { genre ->
            Genre(
                genre = genre,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .width(138.dp)
                    .fillMaxHeight()
            )
        }
    }
}

@Composable
fun Genre(
    genre: Genre,
    modifier: Modifier
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 8.dp,
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = genre.image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
            )

            Text(
                text = genre.name,
                style = MangaTypography.h1,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 20.dp)
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
    Genre("Shonen", R.drawable.img_shonen, "shonen", MangaTypography.body1),
    Genre("Seinen", R.drawable.img_seinen, "seinen", MangaTypography.body1),
    Genre("Shojo", R.drawable.img_shojo, "shojo", MangaTypography.body1)
)
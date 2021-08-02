package com.uwaisalqadri.mangaapp.android.ui.browse.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uwaisalqadri.mangaapp.android.R
import com.uwaisalqadri.mangaapp.android.ui.theme.MangaTypography

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
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = genre.image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
        )

//        Card(
//            modifier = Modifier.fillMaxSize(),
//            elevation = 0.dp,
//            contentColor = Color.White.copy(alpha = 0.5f)
//        ) {
//
//        }

        Text(
            text = genre.name,
            style = MangaTypography.h1,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(top = 20.dp)
        )
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
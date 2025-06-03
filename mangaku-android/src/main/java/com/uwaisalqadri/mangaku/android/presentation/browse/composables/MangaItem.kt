package com.uwaisalqadri.mangaku.android.presentation.browse.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Grade
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.rememberCoilPainter
import com.uwaisalqadri.mangaku.android.presentation.theme.MangaTypography
import com.uwaisalqadri.mangaku.domain.base.model.Manga
import com.uwaisalqadri.mangaku.utils.*

@Composable
fun MangaItem(
    modifier: Modifier = Modifier,
    manga: Manga,
    onReadMore: ((Manga) -> Unit)? = null
) {
    Row(modifier = modifier) {
        Card(shape = RoundedCornerShape(12.dp)) {
            Image(
                painter = rememberCoilPainter(request = manga.getPosterImage()),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(124.dp)
                    .fillMaxHeight(),
            )
        }

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 20.dp, vertical = 5.dp)
        ) {

            StarRate(
                manga = manga,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Text(
                text = manga.getTitle(),
                fontSize = 18.sp,
                style = MangaTypography.h1,
                maxLines = 2,
                color = MaterialTheme.colors.secondary,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 5.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = formatDate(manga.attributes?.startDate ?: "", DateFormatter.CASUAL_DATE_FORMAT),
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.surface,
                    style = MangaTypography.h1
                )

                Text(
                    text = "Vol.${manga.attributes?.volumeCount}",
                    fontSize = 15.sp,
                    style = MangaTypography.h1,
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier
                        .padding(start = 7.dp)
                )
            }

            Spacer(modifier = Modifier.fillMaxHeight(fraction = 0.3f))

            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
                modifier = Modifier
                    .height(40.dp),
                onClick = { if (onReadMore != null) onReadMore(manga) }
            ) {
                Text(
                    text = "Read More",
                    color = Color.White,
                    style = MangaTypography.h1,
                    fontSize = 15.sp
                )
            }
        }
    }
}


@Composable
fun StarRate(
    modifier: Modifier,
    manga: Manga,
    averageRating: Double = (manga.attributes?.averageRating ?: 0.0)
) {
    Row(
        modifier = modifier
    ) {
        repeat(5) { index ->
            Icon(
                imageVector = if (averageRating.toFiveStars() <= index - 1) Icons.Outlined.Grade else Icons.Default.Star,
                contentDescription = null,
                tint = Color.Yellow,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}






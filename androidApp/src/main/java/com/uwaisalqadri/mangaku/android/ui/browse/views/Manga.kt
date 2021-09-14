package com.uwaisalqadri.mangaku.android.ui.browse.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.rememberCoilPainter
import com.uwaisalqadri.mangaku.android.ui.theme.MangaTypography
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.utils.Extensions
import kotlin.math.roundToInt

@Composable
fun Manga(
    manga: Manga,
    modifier: Modifier = Modifier,
    onReadMore: ((Manga) -> Unit)? = null,
) {
    Row(
        modifier = modifier
    ) {
        Card(
            shape = RoundedCornerShape(12.dp)
        ) {
            Image(
                painter = rememberCoilPainter(request = manga.attributes?.posterImage?.original),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(124.dp)
                    .fillMaxHeight(),
            )
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(20.dp, 5.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            StarRate(
                manga = manga,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Text(
                text = getTitle(manga),
                fontSize = 21.sp,
                style = MangaTypography.h1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 11.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = manga.attributes?.startDate ?: "",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    style = MangaTypography.h1
                )

                Text(
                    text = "Vol.${manga.attributes?.volumeCount}",
                    fontSize = 15.sp,
                    style = MangaTypography.h1,
                    modifier = Modifier
                        .padding(start = 7.dp)
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
                modifier = Modifier.height(40.dp),
                onClick = {
                    if (onReadMore != null) onReadMore(manga)
                }
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
    extensions: Extensions = Extensions,
    averageRating: Double = (manga.attributes?.averageRating ?: 0.0)
) {
    Row(
        modifier = modifier
    ) {
        repeat(5) { index ->
            Icon(
                imageVector =
                if (extensions.toFiveStars(averageRating) <= index) Icons.Default.Star
                else Icons.Default.Email, // TODO: change to not-filled star
                contentDescription = null,
                tint = Color.Yellow,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

private fun getTitle(manga: Manga): String {
    val title = manga.attributes?.titles
    return title?.ja_jp ?: title?.en_jp ?: title?.en_us ?: title?.en ?: ""
}






package com.uwaisalqadri.mangaku.android.ui.mymanga.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.rememberCoilPainter
import com.uwaisalqadri.mangaku.android.ui.theme.MangaTypography
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.utils.Extensions

@Composable
fun MyMangaGridItem(
    manga: Manga,
    extension: Extensions = Extensions,
    onClick: (Manga) -> Unit
) {
    Box(
        modifier = Modifier.padding(start = 10.dp, bottom = 20.dp)
    ) {
        Image(
            painter = rememberCoilPainter(request = extension.getPosterImage(manga)),
            contentDescription = null,
            modifier = Modifier
                .size(width = 118.dp, height = 177.dp)
        )

        Column(horizontalAlignment = Alignment.End) {
            Spacer(modifier = Modifier.aspectRatio(1.4f))

            Row(horizontalArrangement = Arrangement.End) {
                Card(
                    shape = RoundedCornerShape(6.dp),
                    elevation = 15.dp,
                    modifier = Modifier
                        .size(117.dp, 107.dp)
                        .clickable { onClick(manga) }
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(
                            text = extension.getTitle(manga),
                            color = Color.Black,
                            style = MangaTypography.h1,
                            fontSize = 15.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text(
                            text = "Volume ${manga.attributes?.volumeCount}",
                            color = Color.DarkGray,
                            style = MangaTypography.h2,
                            fontSize = 10.sp
                        )

                        Spacer(modifier = Modifier.aspectRatio(7f, true))

                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                text = "Read More",
                                color = Color.Black,
                                style = MangaTypography.h1,
                                fontSize = 10.sp
                            )

                            Icon(
                                imageVector = Icons.Default.ArrowForwardIos,
                                contentDescription = null,
                                tint = Color.Black,
                                modifier = Modifier.size(15.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}












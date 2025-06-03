package com.uwaisalqadri.mangaku.android.presentation.mymanga.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.rememberCoilPainter
import com.uwaisalqadri.mangaku.android.presentation.theme.MangaTypography
import com.uwaisalqadri.mangaku.domain.base.model.Manga
import com.uwaisalqadri.mangaku.utils.getPosterImage
import com.uwaisalqadri.mangaku.utils.getTitle

@Composable
fun MyMangaGridItem(
    manga: Manga,
    onClick: (Manga) -> Unit
) {
    Box(
        modifier = Modifier.padding(start = 10.dp, bottom = 20.dp)
    ) {
        Image(
            painter = rememberCoilPainter(request = manga.getPosterImage()),
            contentDescription = null,
            modifier = Modifier
                .size(width = 118.dp, height = 177.dp)
        )

        Column(horizontalAlignment = Alignment.End) {
            Spacer(modifier = Modifier.aspectRatio(ratio = 1.4f))

            Row(horizontalArrangement = Arrangement.End) {
                Card(
                    shape = RoundedCornerShape(6.dp),
                    elevation = 15.dp,
                    modifier = Modifier
                        .size(117.dp, 107.dp)
                        .clickable { onClick(manga) }
                ) {
                    Column(
                        modifier = Modifier
                            .background(color = MaterialTheme.colors.primaryVariant)
                            .padding(10.dp)
                    ) {
                        Text(
                            text = manga.getTitle(),
                            color = MaterialTheme.colors.secondary,
                            style = MangaTypography.h1,
                            fontSize = 15.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text(
                            text = "Volume ${manga.attributes?.volumeCount}",
                            color = MaterialTheme.colors.surface,
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
                                color = MaterialTheme.colors.secondary,
                                style = MangaTypography.h1,
                                fontSize = 10.sp
                            )

                            Icon(
                                imageVector = Icons.Default.ArrowForwardIos,
                                contentDescription = null,
                                tint = MaterialTheme.colors.secondary,
                                modifier = Modifier.size(15.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
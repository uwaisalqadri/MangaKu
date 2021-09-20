package com.uwaisalqadri.mangaku.android.ui.mymanga.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.uwaisalqadri.mangaku.android.ui.browse.composables.StarRate
import com.uwaisalqadri.mangaku.android.ui.theme.MangaTypography
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.utils.Extensions
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerWithTransition(
    manga: List<Manga>,
    extension: Extensions = Extensions,
    modifier: Modifier
) {
    HorizontalPager(
        state = PagerState(
            pageCount = manga.size,
            currentPage = 0,
            infiniteLoop = manga.size != 1,
        ),
        modifier = modifier,
    ) { page ->
        Card(
            shape = RoundedCornerShape(5.dp),
            elevation = 10.dp,
            modifier = Modifier
                .graphicsLayer {
                    // Calculate the absolute offset for the current page from the
                    // scroll position. We use the absolute value which allows us to mirror
                    // any effects for both directions
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                    // We animate the scaleX + scaleY, between 85% and 100%
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }

                    // We animate the alpha, between 50% and 100%
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
                .width(240.dp)
                .height(370.dp)
        ) {
            Image(
                painter = rememberCoilPainter(request = extension.getPosterImage(manga[page])),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .background(brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        )
                    ))
            )

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.padding(start = 15.dp)
            ) {

                Text(
                    text = extension.getTitle(manga[page]),
                    fontSize = 40.sp,
                    style = MangaTypography.overline,
                    maxLines = 2,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                Text(
                    text = "Volume ${manga[page].attributes?.volumeCount}",
                    fontSize = 15.sp,
                    style = MangaTypography.h1,
                    color = Color.White
                )

                StarRate(
                    manga = manga[page],
                    extension = extension,
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 30.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}















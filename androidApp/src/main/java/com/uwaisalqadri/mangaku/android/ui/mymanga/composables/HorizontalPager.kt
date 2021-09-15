package com.uwaisalqadri.mangaku.android.ui.mymanga.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import androidx.compose.ui.util.lerp
import com.google.accompanist.coil.rememberCoilPainter
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.utils.getPosterImage
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerWithTransition(
    manga: List<Manga>,
    onSlide: (Int) -> Unit
) {
    HorizontalPager(
        state = PagerState(
            pageCount = manga.size,
            currentPage = 0,
            infiniteLoop = true,
        ),
        modifier = Modifier
            .fillMaxSize(),
    ) { page ->
        Card(
            shape = RoundedCornerShape(0.dp),
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
            onSlide(page)
            Image(
                painter = rememberCoilPainter(request = manga[page].getPosterImage()),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}















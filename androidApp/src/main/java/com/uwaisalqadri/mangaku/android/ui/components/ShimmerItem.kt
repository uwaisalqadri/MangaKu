package com.uwaisalqadri.mangaku.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerItem(
    modifier: Modifier,
    colors: List<Color>,
    xShimmer: Float,
    yShimmer: Float,
    gradientWidth: Float
) {

    val brush = Brush.linearGradient(
        colors,
        start = Offset(xShimmer - gradientWidth, yShimmer - gradientWidth),
        end = Offset(xShimmer, yShimmer)
    )

    Row(
        modifier = modifier
    ) {
        Card(
            shape = RoundedCornerShape(12.dp)
        ) {
            Surface(shape = MaterialTheme.shapes.small) {
                Spacer(
                    modifier = Modifier
                        .width(124.dp)
                        .fillMaxHeight()
                        .background(brush = brush)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(20.dp, 5.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Surface(shape = MaterialTheme.shapes.small) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .background(brush = brush)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 11.dp)
                    .fillMaxWidth()
            ) {
                Surface(shape = MaterialTheme.shapes.small) {
                    Spacer(
                        modifier = Modifier
                            .width(40.dp)
                            .height(20.dp)
                            .background(brush = brush)
                    )
                }

                Surface(shape = MaterialTheme.shapes.small) {
                    Spacer(
                        modifier = Modifier
                            .width(40.dp)
                            .height(20.dp)
                            .background(brush = brush)
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            Surface(shape = MaterialTheme.shapes.small) {
                Spacer(
                    modifier = Modifier
                        .width(100.dp)
                        .height(40.dp)
                        .background(brush = brush)
                )
            }
        }
    }
}
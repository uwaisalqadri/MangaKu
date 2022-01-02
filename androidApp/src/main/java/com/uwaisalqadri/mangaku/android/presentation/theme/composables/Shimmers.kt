package com.uwaisalqadri.mangaku.android.presentation.theme.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun ShimmerDetail() {
    Column(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        Card(
            shape = RoundedCornerShape(9.dp),
            elevation = 0.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, bottom = 16.dp)
                .height(200.dp)
        ) {
            Shimmer(modifier = Modifier.fillMaxSize())
        }

        Shimmer(
            cornerRadius = 8.dp,
            modifier = Modifier
                .width(250.dp)
                .height(25.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Shimmer(
            cornerRadius = 8.dp,
            modifier = Modifier
                .width(300.dp)
                .height(15.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 5.dp)
        ) {

            Shimmer(
                cornerRadius = 8.dp,
                modifier = Modifier
                    .width(100.dp)
                    .height(30.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.Yellow
                )

                Shimmer(
                    cornerRadius = 8.dp,
                    modifier = Modifier
                        .width(70.dp)
                        .height(20.dp)
                )
            }

        }

        Spacer(modifier = Modifier.height(50.dp))

        Shimmer(
            cornerRadius = 8.dp,
            modifier = Modifier
                .width(200.dp)
                .height(40.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        repeat(5) {
            Shimmer(
                cornerRadius = 8.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))
        }

        Spacer(modifier = Modifier.height(200.dp))
    }
}

@Composable
fun ShimmerBrowseItem() {
    Row(
        modifier = Modifier.padding(20.dp)
    ) {
        Shimmer(
            modifier = Modifier
                .width(124.dp)
                .height(197.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(20.dp, 5.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Shimmer(
                cornerRadius = 8.dp,
                modifier = Modifier
                    .width(100.dp)
                    .height(20.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Shimmer(
                cornerRadius = 8.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )

            Spacer(modifier = Modifier.height(11.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Shimmer(
                    cornerRadius = 8.dp,
                    modifier = Modifier
                        .width(50.dp)
                        .height(20.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Shimmer(
                    cornerRadius = 8.dp,
                    modifier = Modifier
                        .width(50.dp)
                        .height(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(70.dp))

            Shimmer(
                cornerRadius = 8.dp,
                modifier = Modifier
                    .width(100.dp)
                    .height(40.dp)
            )
        }
    }

}

@Composable
fun ShimmerSearchItem() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 20.dp)
    ) {
        Shimmer(
            modifier = Modifier
                .height(130.dp)
                .width(90.dp)
        )
    }
}

@Composable
fun Shimmer(
    modifier: Modifier,
    cornerRadius: Dp = 12.dp
) {
    Card(
        shape = RoundedCornerShape(cornerRadius),
        elevation = 0.dp
    ) {
        Box(modifier = modifier
            .shimmer()
            .background(Color.Gray.copy(0.5f))
        )
    }
}













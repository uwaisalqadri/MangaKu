package com.uwaisalqadri.mangaku.android.ui.mymanga.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uwaisalqadri.mangaku.android.R

@Composable
fun LayoutSwitch(
    modifier: Modifier,
    onSwitch: (Boolean) -> Unit
) {
    var isSlide by remember { mutableStateOf(true) }

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Card(
            backgroundColor = if (isSlide) Color.LightGray else Color.White,
            shape = RoundedCornerShape(5.dp),
            elevation = 5.dp,
            modifier = Modifier
                .width(50.dp)
                .fillMaxHeight()
                .padding(end = 5.dp)
                .clickable {
                    isSlide = true
                    onSwitch(isSlide)
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_slide),
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .padding(3.dp)
            )
        }

        Card(
            backgroundColor = if (!isSlide) Color.LightGray else Color.White,
            shape = RoundedCornerShape(5.dp),
            elevation = 5.dp,
            modifier = Modifier
                .width(55.dp)
                .fillMaxHeight()
                .padding(start = 5.dp)
                .clickable {
                    isSlide = false
                    onSwitch(isSlide)
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_stack),
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .padding(10.dp)
            )
        }

    }
}
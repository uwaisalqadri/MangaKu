package com.uwaisalqadri.mangaku.android.presentation.mymanga.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uwaisalqadri.mangaku.android.presentation.theme.R

@Composable
fun LayoutSwitch(
    modifier: Modifier,
    onSwitch: (Boolean) -> Unit
) {
    var isSlide by rememberSaveable { mutableStateOf(true) }

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        val activeColor = MaterialTheme.colors.surface
        val inactiveColor = MaterialTheme.colors.primaryVariant

        Card(
            backgroundColor = if (isSlide) activeColor else inactiveColor,
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
                painter = painterResource(id = R.Drawable.SlideIcon),
                contentDescription = null,
                tint = MaterialTheme.colors.secondary,
                modifier = Modifier
                    .size(20.dp)
                    .padding(3.dp)
            )
        }

        Card(
            backgroundColor = if (!isSlide) activeColor else inactiveColor,
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
                painter = painterResource(id = R.Drawable.StackIcon),
                contentDescription = null,
                tint = MaterialTheme.colors.secondary,
                modifier = Modifier
                    .size(20.dp)
                    .padding(10.dp)
            )
        }

    }
}
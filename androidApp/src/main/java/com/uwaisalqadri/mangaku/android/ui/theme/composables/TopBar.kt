package com.uwaisalqadri.mangaku.android.ui.theme.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uwaisalqadri.mangaku.android.ui.theme.MangaTypography

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    name: String,
    @DrawableRes icon: Int? = null,
    onIconClick: (() -> Unit)? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
            .padding(top = 25.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = name,
            style = MangaTypography.h1,
            color = MaterialTheme.colors.secondary,
            fontSize = 25.sp
        )

        Spacer(modifier = Modifier.width(if (icon != null) 100.dp else 200.dp))

        if (icon != null) {
            Icon(
                painter = painterResource(id = icon),
                tint = MaterialTheme.colors.secondary,
                contentDescription = null,
                modifier = Modifier.clickable {
                    if (onIconClick != null) onIconClick()
                }
            )
        }
    }
}
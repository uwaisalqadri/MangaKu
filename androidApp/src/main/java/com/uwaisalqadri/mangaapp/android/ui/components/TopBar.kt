package com.uwaisalqadri.mangaapp.android.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uwaisalqadri.mangaapp.android.R
import com.uwaisalqadri.mangaapp.android.ui.theme.MangaTypography

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
            fontSize = 23.sp
        )

        Spacer(modifier = Modifier.width(100.dp))

        if (icon != null) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                Modifier.clickable {
                    if (onIconClick != null) onIconClick()
                }
            )
        }
    }
}
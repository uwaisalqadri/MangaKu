package com.uwaisalqadri.mangaku.android.presentation.theme.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uwaisalqadri.mangaku.android.presentation.theme.MangaTypography

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
            Button(
                elevation = ButtonDefaults.elevation(0.dp, 0.dp),
                onClick = {
                    if (onIconClick != null) onIconClick()
                }
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    tint = MaterialTheme.colors.secondary,
                    contentDescription = null
                )
            }
        }

    }
}
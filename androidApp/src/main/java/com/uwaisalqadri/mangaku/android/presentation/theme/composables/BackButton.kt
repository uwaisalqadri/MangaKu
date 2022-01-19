package com.uwaisalqadri.mangaku.android.presentation.theme.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uwaisalqadri.mangaku.android.presentation.theme.MangaTypography

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Button(
        elevation = ButtonDefaults.elevation(0.dp, 0.dp),
        onClick = {
          onBackClick()
        }
    ) {
        Row {
            Icon(
                imageVector = Icons.Rounded.ArrowBackIos,
                contentDescription = "Back",
                tint = MaterialTheme.colors.secondary,
                modifier = Modifier
                    .size(18.dp)
            )

            Text(
                text = "Back",
                color = MaterialTheme.colors.secondary,
                style = MangaTypography.h1,
                fontSize = 14.sp
            )
        }
    }

}
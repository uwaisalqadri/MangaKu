package com.uwaisalqadri.mangaku.android.ui.detail.composables

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.uwaisalqadri.mangaku.android.ui.theme.MangaTypography

@Composable
fun FavoriteDialog(
    message: String,
    showDialog: Boolean,
    modifier: Modifier = Modifier,
    setShowDialog: (Boolean) -> Unit
) {
    val handler = Handler(Looper.getMainLooper())

    if (showDialog) {
        Dialog(onDismissRequest = {}) {

            handler.postDelayed({
                setShowDialog(false)
            }, 1500)

            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = 8.dp,
                modifier = modifier
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier
                            .size(26.dp)
                    )

                    Text(
                        text = message,
                        color = Color.Black,
                        style = MangaTypography.h1,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    )
                }
            }
        }
    }
}














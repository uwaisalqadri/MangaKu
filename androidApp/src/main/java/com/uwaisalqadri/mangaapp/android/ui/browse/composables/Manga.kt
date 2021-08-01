package com.uwaisalqadri.mangaapp.android.ui.browse.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.rememberCoilPainter
import com.uwaisalqadri.mangaapp.data.souce.remote.response.Manga

@Composable
fun Manga(
    manga: Manga,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Card(
            shape = RoundedCornerShape(12.dp)
        ) {
            Image(
                painter = rememberCoilPainter(request = manga.attributes.posterImage.original),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(124.dp)
                    .fillMaxHeight(),
            )
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(20.dp, 5.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = manga.attributes.titles.en_jp,
                fontSize = 21.sp
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 11.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "04-05-2019",
                    fontSize = 12.sp
                )

                Text(
                    text = "Vol.${manga.attributes.volumeCount}",
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(start = 7.dp)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
            ) {
                Text(text = "Read More", color = Color.White)
            }
        }
    }
}







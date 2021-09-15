package com.uwaisalqadri.mangaku.android.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.rememberCoilPainter
import com.uwaisalqadri.mangaku.android.R
import com.uwaisalqadri.mangaku.android.ui.composables.TopBar
import com.uwaisalqadri.mangaku.android.ui.theme.MangaTypography
import com.uwaisalqadri.mangaku.android.utils.getTitle
import org.koin.androidx.compose.getViewModel

@Composable
fun DetailScreen(
    mangaId: String,
    viewModel: DetailViewModel = getViewModel(),
) {
    val manga by viewModel.manga.collectAsState()
    viewModel.fetchDetailManga(mangaId)

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {

        Row(
            modifier = Modifier.padding(start = 25.dp, top = 25.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIos,
                contentDescription = "Back",
                tint = Color.Black,
                modifier = Modifier.size(18.dp)
            )

            Text(
                text = "Back",
                color = Color.Black,
                style = MangaTypography.h3,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.padding(top = 30.dp))

        TopBar(name = "Detail")

        Card(
            shape = RoundedCornerShape(9.dp),
            elevation = 0.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, end = 25.dp, top = 30.dp, bottom = 16.dp)
                .height(200.dp)
        ) {
            Image(
                painter = rememberCoilPainter(request = manga.attributes?.coverImage?.original),
                contentDescription = "cover image",
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = manga.getTitle(),
            color = Color.Black,
            style = MangaTypography.h1,
            fontSize = 23.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        )

        Text(
            text = "Action, Adventure, RPG",
            color = Color.Black,
            style = MangaTypography.h3,
            fontSize = 15.sp,
            modifier = Modifier
                .padding(horizontal = 30.dp, vertical = 8.dp)
        )

        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 5.dp)
        ) {
            Card(
                elevation = 0.dp,
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(
                    text = manga.attributes?.startDate ?: "",
                    color = Color.White,
                    style = MangaTypography.h1,
                    fontSize = 13.sp,
                    modifier = Modifier
                        .background(Color.Black)
                        .padding(horizontal = 15.dp, vertical = 5.dp)
                )
            }

            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.Yellow
                )

                Text(
                    text = (manga.attributes?.averageRating ?: 0.0).toString(),
                    color = Color.Black,
                    style = MangaTypography.h2,
                    fontSize = 13.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Description",
            color = Color.Black,
            style = MangaTypography.h2,
            fontSize = 21.sp,
            modifier = Modifier.padding(horizontal = 30.dp)
        )

        Text(
            text = manga.attributes?.synopsis ?: "",
            color = Color.Black,
            style = MangaTypography.h3,
            fontSize = 15.sp,
            modifier = Modifier.padding(top = 15.dp, start = 30.dp, end = 30.dp)
        )

        Spacer(modifier = Modifier.height(200.dp))

    }
}















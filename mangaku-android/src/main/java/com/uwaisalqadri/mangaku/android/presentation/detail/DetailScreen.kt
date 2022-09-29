package com.uwaisalqadri.mangaku.android.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.rememberCoilPainter
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uwaisalqadri.mangaku.android.presentation.detail.composables.FavoriteDialog
import com.uwaisalqadri.mangaku.android.presentation.mymanga.MyMangaViewModel
import com.uwaisalqadri.mangaku.android.presentation.theme.MangaTypography
import com.uwaisalqadri.mangaku.android.presentation.theme.composables.BackButton
import com.uwaisalqadri.mangaku.android.presentation.theme.composables.ShimmerDetail
import com.uwaisalqadri.mangaku.android.presentation.theme.composables.TopBar
import com.uwaisalqadri.mangaku.android.utils.ComposableObserver
import com.uwaisalqadri.mangaku.android.utils.getValue
import com.uwaisalqadri.mangaku.android.utils.isLoading
import com.uwaisalqadri.mangaku.domain.model.Manga
import com.uwaisalqadri.mangaku.utils.*
import org.koin.androidx.compose.getViewModel

@Destination
@Composable
fun DetailScreen(
    navigator: DestinationsNavigator,
    mangaId: String,
    viewModel: DetailViewModel = getViewModel(),
    mangaViewModel: MyMangaViewModel = getViewModel()
) {
    val detailMangaState by viewModel.detailManga.collectAsState()
    val favState by mangaViewModel.favState.collectAsState()

    val (isFavorite, setFavorite) = remember { mutableStateOf(false) }
    val (isShowDialog, setShowDialog) = remember { mutableStateOf(false) }

    ComposableObserver {
        viewModel.getDetailManga(mangaId)
        mangaViewModel.checkFavorite(mangaId)
    }

    LazyColumn(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .background(color = MaterialTheme.colors.primary)
            .fillMaxSize()
    ) {
        item {
            FavoriteDialog(
                message = if (isFavorite) "Added to Favorite" else "Removed from Favorite",
                isShowDialog = isShowDialog,
                setShowDialog = setShowDialog,
                modifier = Modifier.size(134.dp)
            )
        }

        item {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, top = 25.dp, end = 25.dp)
            ) {
                BackButton {
                    navigator.popBackStack()
                }

                Button(
                    elevation = ButtonDefaults.elevation(0.dp, 0.dp),
                    onClick = {
                        setShowDialog(true)
                        if (!detailMangaState.isLoading()) {
                            getValue(detailMangaState)?.let {
                                if (isFavorite) mangaViewModel.deleteMyManga(it.id)
                                else mangaViewModel.addMyManga(it)
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier.size(25.dp),
                    )
                }
            }
        }

        item {
            TopBar(
                name = "Detail",
                modifier = Modifier.padding(top = 20.dp)
            )
        }

        item {
            if (detailMangaState.isLoading()) {
                ShimmerDetail()
            } else {
                when {
                    favState.favMangaFound -> setFavorite(true)
                    favState.addFavorite -> setFavorite(true)
                    favState.removeFavorite -> setFavorite(false)
                    else -> setFavorite(false)
                }

                getValue(detailMangaState)?.let {
                    MangaDetail(manga = it)
                }

                Spacer(modifier = Modifier.height(200.dp))
            }
        }
    }
}

@Composable
fun MangaDetail(
    manga: Manga
) {
    Card(
        shape = RoundedCornerShape(9.dp),
        elevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp, top = 30.dp, bottom = 16.dp)
            .height(200.dp)
    ) {
        Image(
            painter = rememberCoilPainter(request = manga.getCoverImage()),
            contentDescription = "cover image",
            contentScale = ContentScale.Crop
        )
    }

    Text(
        text = manga.getTitle(),
        color = MaterialTheme.colors.secondary,
        style = MangaTypography.h1,
        fontSize = 23.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    )

    Text(
        text = manga.attributes?.slug ?: "",
        color = MaterialTheme.colors.secondary,
        style = MangaTypography.h3,
        fontSize = 15.sp,
        modifier = Modifier
            .padding(start = 30.dp, end = 30.dp, bottom = 10.dp)
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
                text = formatDate(manga.attributes?.startDate ?: "", Configs.CASUAL_DATE_FORMAT),
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
                color = MaterialTheme.colors.secondary,
                style = MangaTypography.h2,
                fontSize = 13.sp
            )
        }
    }

    Text(
        text = "Description",
        color = MaterialTheme.colors.secondary,
        style = MangaTypography.h2,
        fontSize = 21.sp,
        modifier = Modifier.padding(
            start = 30.dp,
            end = 30.dp,
            top = 50.dp
        )
    )

    Text(
        text = manga.attributes?.synopsis ?: "",
        color = MaterialTheme.colors.secondary,
        style = MangaTypography.h3,
        fontSize = 15.sp,
        modifier = Modifier.padding(
            top = 15.dp,
            start = 30.dp,
            end = 30.dp
        )
    )
}
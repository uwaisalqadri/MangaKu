package com.uwaisalqadri.mangaku.android.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.accompanist.coil.rememberCoilPainter
import com.uwaisalqadri.mangaku.android.ui.composables.BackButton
import com.uwaisalqadri.mangaku.android.ui.composables.ShimmerDetail
import com.uwaisalqadri.mangaku.android.ui.composables.TopBar
import com.uwaisalqadri.mangaku.android.ui.detail.composables.FavoriteDialog
import com.uwaisalqadri.mangaku.android.ui.mymanga.MyMangaViewModel
import com.uwaisalqadri.mangaku.android.ui.theme.MangaTheme
import com.uwaisalqadri.mangaku.android.ui.theme.MangaTypography
import com.uwaisalqadri.mangaku.utils.Constants
import com.uwaisalqadri.mangaku.utils.Extensions
import com.uwaisalqadri.mangaku.utils.formatDate
import org.koin.androidx.compose.getViewModel

class DetailFragment: Fragment() {

    companion object {
        const val DETAIL = "detail"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val mangaId = arguments?.getString(DETAIL)
                DetailScreen(mangaId = mangaId ?: "")
            }
        }
    }

    @Composable
    fun DetailScreen(
        mangaId: String,
        viewModel: DetailViewModel = getViewModel(),
        mangaViewModel: MyMangaViewModel = getViewModel(),
        extension: Extensions = Extensions
    ) {
        viewModel.getDetailManga(mangaId)
        mangaViewModel.checkFavorite(mangaId)

        val manga by viewModel.detailManga
        val loading by viewModel.loading
        val favState by mangaViewModel.favState.observeAsState()

        val (isFavorite, setFavorite) = remember { mutableStateOf(false) }
        val (showDialog, setShowDialog) = remember { mutableStateOf(false) }

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start
        ) {

            FavoriteDialog(
                message = if (isFavorite) "Added to Favorite" else "Removed from Favorite",
                showDialog = showDialog,
                setShowDialog = setShowDialog,
                modifier = Modifier.size(134.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, top = 25.dp, end = 25.dp)
            ) {
                BackButton {
                    findNavController().popBackStack()
                }

                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            setShowDialog(true)
                            if (!loading) {
                                if (isFavorite) mangaViewModel.deleteMyManga(manga.id)
                                else mangaViewModel.addMyManga(manga)
                            }
                        }
                )
            }

            Spacer(modifier = Modifier.padding(top = 20.dp))

            TopBar(name = "Detail")

            if (loading) {
                ShimmerDetail()
            } else {

                when {
                    favState?.favMangaFound == true -> setFavorite(true)
                    favState?.addFavorite == true -> setFavorite(true)
                    favState?.removeFavorite == true -> setFavorite(false)
                    else -> setFavorite(false)
                }

                Card(
                    shape = RoundedCornerShape(9.dp),
                    elevation = 0.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp, end = 25.dp, top = 30.dp, bottom = 16.dp)
                        .height(200.dp)
                ) {
                    Image(
                        painter = rememberCoilPainter(request = extension.getCoverImage(manga)),
                        contentDescription = "cover image",
                        contentScale = ContentScale.Crop
                    )
                }

                Text(
                    text = extension.getTitle(manga),
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
                            text = formatDate(manga.attributes?.startDate ?: "", Constants.casualDateFormat),
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
    }
}
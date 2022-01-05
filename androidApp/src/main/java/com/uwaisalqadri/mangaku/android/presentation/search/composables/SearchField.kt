package com.uwaisalqadri.mangaku.android.presentation.search.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uwaisalqadri.mangaku.android.R
import com.uwaisalqadri.mangaku.android.presentation.theme.MangaTypography
import com.uwaisalqadri.mangaku.android.presentation.theme.Montserrat

@Composable
fun SearchField(
    query: String,
    placeholder: String,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    onEraseQuery: () -> Unit,
    modifier: Modifier
) {
    Card(
        backgroundColor = MaterialTheme.colors.onSurface,
        shape = RoundedCornerShape(20.dp),
        elevation = 0.dp,
        modifier = modifier
    ) {

        var text by remember { mutableStateOf(placeholder) }

        Text(
            text = if (query.isEmpty()) text else "",
            style = MangaTypography.h3,
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .fillMaxSize()
                .clickable { text = "" }
                .padding(
                    start = 60.dp,
                    end = 10.dp,
                    top = 11.dp,
                    bottom = 5.dp
                )
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                tint = MaterialTheme.colors.secondary,
                modifier = Modifier
                    .size(40.dp)
                    .padding(start = 10.dp)
            )

            BasicTextField(
                value = query,
                singleLine = true,
                textStyle = TextStyle(
                    color = MaterialTheme.colors.secondary,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp
                ),

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    imeAction = ImeAction.Search
                ),

                keyboardActions = KeyboardActions(
                    onSearch = {
                        onExecuteSearch()
                    }
                ),

                onValueChange = {
                    onQueryChanged(it)
                    text = ""
                },

                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 11.dp, bottom = 5.dp)
                    .width(230.dp)
                    .height(30.dp)
            )

            if (query != "") {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                    contentDescription = null,
                    tint = MaterialTheme.colors.secondary,
                    modifier = Modifier
                        .size(30.dp)
                        .padding(end = 10.dp)
                        .clickable {
                            onEraseQuery()
                        }
                )
            }
        }
    }
}
















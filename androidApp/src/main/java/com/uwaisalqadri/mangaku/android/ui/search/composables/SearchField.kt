package com.uwaisalqadri.mangaku.android.ui.search.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uwaisalqadri.mangaku.android.R
import com.uwaisalqadri.mangaku.android.ui.theme.MangaTypography

@Composable
fun SearchField(
    query: String,
    placeholder: String,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    modifier: Modifier
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = 0.dp,
        modifier = modifier
    ) {

        var text by remember { mutableStateOf(placeholder) }

        Text(
            text = if (query.isEmpty()) text else "",
            style = MangaTypography.h3,
            color = Color.DarkGray,
            modifier = Modifier
                .padding(start = 60.dp, end = 10.dp, top = 11.dp, bottom = 5.dp)
                .fillMaxWidth()
                .height(30.dp)
                .clickable { text = "" }
        )

        Row(
            modifier = Modifier
                .background(Color.LightGray.copy(alpha = 0.3f))
        ) {
            Spacer(modifier = Modifier.width(10.dp))

            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                tint = Color.DarkGray,
                modifier = Modifier
                    .size(40.dp)
                    .padding(start = 10.dp, top = 5.dp)
            )

            BasicTextField(
                value = query,
                textStyle = MangaTypography.h3,
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
                    .fillMaxWidth()
                    .height(30.dp)
            )
        }
    }
}
















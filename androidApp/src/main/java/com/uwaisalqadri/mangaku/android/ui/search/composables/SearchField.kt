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
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    modifier: Modifier
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = 0.dp,
        modifier = modifier
    ) {

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
                    .clickable { onExecuteSearch() }
            )

            BasicTextField(
                value = query,
                onValueChange = { onQueryChanged(it) },
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

                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 5.dp)
                    .fillMaxWidth()
                    .height(30.dp)
            )
        }
    }
}
















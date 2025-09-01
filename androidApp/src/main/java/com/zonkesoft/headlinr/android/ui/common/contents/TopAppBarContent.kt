package com.zonkesoft.headlinr.android.ui.common.contents

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.zonkesoft.headlinr.android.R
import com.zonkesoft.headlinr.android.ui.common.texts.BoldText
import com.zonkesoft.headlinr.android.ui.theme.MyApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarContent(
    title: String,
    textColor: Color,
    scrollBehavior: TopAppBarScrollBehavior,
    topBarColor: Color,
    onMenuClick: () -> Unit = {},
    onSearchClicked: () -> Unit = {},
    onProfileClicked: () -> Unit = {},
) {
    return SmallTopAppBar(
        title = {
            BoldText(
                text = title,
                textColor = textColor,
                fontSize = 20.sp,
                textAlign = TextAlign.Start
            )
        },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            Row {
                IconButton(onClick = onSearchClicked) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                }

                IconButton(onClick = onProfileClicked) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "profile",
                        //modifier = Modifier.height(50.dp).width(50.dp)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = topBarColor
        ),
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Light",
    heightDp = 100
)
@Preview(
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark"
)
@Composable
fun HomeScreenPreview() {
    MyApplicationTheme {
        TopAppBarContent(
            title = stringResource(R.string.for_you),
            textColor = MaterialTheme.colorScheme.onBackground,
            onMenuClick = { /* Handle drawer or nav */ },
            onSearchClicked = { /* Navigate to search */ },
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState()),
            topBarColor = MaterialTheme.colorScheme.primaryContainer
        )
    }
}
package com.zonkesoft.headlinr.android.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zonkesoft.headlinr.android.ui.theme.MyApplicationTheme

@Composable
fun HomeScreen(navController: NavHostController, textColor: Color, backgroundColor: Color) {

    Box(
        modifier = Modifier.fillMaxSize().background(backgroundColor).padding(16.dp)
    ) {

    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Light"
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
        HomeScreen(rememberNavController(),
            MaterialTheme.colorScheme.onBackground,
            MaterialTheme.colorScheme.background
        )
    }
}
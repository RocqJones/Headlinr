package com.zonkesoft.headlinr.android.ui.screens

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zonkesoft.headlinr.android.ui.theme.MyApplicationTheme

@Composable
fun HomeScreen(navController: NavHostController) {
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    widthDp = 450,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Light"
)
@Preview(
    showSystemUi = true,
    showBackground = true,
    widthDp = 450,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark"
)

@Composable
fun HomeScreenPreview() {
    MyApplicationTheme {
        HomeScreen(rememberNavController())
    }
}
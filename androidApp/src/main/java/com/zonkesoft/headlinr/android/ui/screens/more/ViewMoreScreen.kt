package com.zonkesoft.headlinr.android.ui.screens.more

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.zonkesoft.headlinr.presentation.vm.NewsViewModel

@Composable
fun ViewMoreScreen(
    navController: NavHostController,
    textColor: Color,
    backgroundColor: Color,
    newsViewModel: NewsViewModel
) {
    val mainModel = newsViewModel.articlesModel.collectAsState()

    LaunchedEffect(mainModel) {
        Log.d("ViewMoreScreen", "mainModel: ${mainModel.value}")
    }
}
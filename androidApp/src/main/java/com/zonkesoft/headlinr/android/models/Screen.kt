package com.zonkesoft.headlinr.android.models

sealed class Screen(val route: String) {
    data object SplashScreen : Screen("splash_screen")
    data object HomeScreen : Screen("home_screen")
}
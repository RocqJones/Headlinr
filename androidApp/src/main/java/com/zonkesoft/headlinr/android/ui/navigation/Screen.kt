package com.zonkesoft.headlinr.android.ui.navigation

/**
 * Screen.kt: This file defines the navigation routes for the app's screens.
 */
sealed class Screen(val route: String) {
    data object SplashScreen : Screen("splash_screen")
    data object HomeScreen : Screen("home_screen")
    data object ViewMoreScreen : Screen("view_more_screen")
    data object ViewAllScreen : Screen("view_all_screen")
    data object SearchScreen : Screen("search_screen")
}
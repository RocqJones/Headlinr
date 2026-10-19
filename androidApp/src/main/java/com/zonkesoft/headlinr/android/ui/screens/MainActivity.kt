package com.zonkesoft.headlinr.android.ui.screens

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zonkesoft.headlinr.android.ui.navigation.Screen
import com.zonkesoft.headlinr.android.ui.screens.more.ViewAllScreen
import com.zonkesoft.headlinr.android.ui.screens.more.ViewMoreScreen
import com.zonkesoft.headlinr.android.ui.theme.MyApplicationTheme
import com.zonkesoft.headlinr.presentation.vm.InterfaceViewModel
import com.zonkesoft.headlinr.presentation.vm.NewsViewModel
import com.zonkesoft.headlinr.presentation.vm.SearchViewModel
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false) // Allow drawing behind system bars

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationGraph()
                }
            }
        }
    }
}

@Composable
fun NavigationGraph(
    interfaceViewModel: InterfaceViewModel = getViewModel(),
    newsViewModel: NewsViewModel = getViewModel(),
    searchViewModel: SearchViewModel = getViewModel()
) {
    val currentContext = LocalContext.current
    val textColor = MaterialTheme.colorScheme.onBackground
    val backgroundColor = MaterialTheme.colorScheme.background
    val navController = rememberNavController()

    NavHost(
        navController, startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController, textColor, backgroundColor)
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController, textColor, backgroundColor, newsViewModel, interfaceViewModel)
        }
        composable(Screen.ViewMoreScreen.route) {
            // Foreground content, padded for status bar
            Box(
                modifier = Modifier.fillMaxSize().padding(WindowInsets.statusBars.asPaddingValues())
            ) {
                ViewMoreScreen(navController, textColor, backgroundColor, newsViewModel)
            }
        }
        composable(Screen.ViewAllScreen.route) {
            Box(
                modifier = Modifier.fillMaxSize().padding(WindowInsets.statusBars.asPaddingValues())
            ) {
                ViewAllScreen(navController, textColor, newsViewModel)
            }
        }
        composable(Screen.SearchScreen.route) {
            Box(
                modifier = Modifier.fillMaxSize().padding(WindowInsets.statusBars.asPaddingValues())
            ) {
                SearchScreen(navController, textColor, searchViewModel, newsViewModel)
            }
        }
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
fun NavigationGraphPreview() {
    MyApplicationTheme {
        NavigationGraph()
    }
}
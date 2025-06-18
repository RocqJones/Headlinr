package com.zonkesoft.headlinr.android.ui.screens

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zonkesoft.headlinr.android.ui.navigation.Screen
import com.zonkesoft.headlinr.android.ui.screens.more.ViewMoreScreen
import com.zonkesoft.headlinr.android.ui.theme.MyApplicationTheme
import com.zonkesoft.headlinr.presentation.vm.InterfaceViewModel
import com.zonkesoft.headlinr.presentation.vm.NewsViewModel
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    newsViewModel: NewsViewModel = getViewModel()
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
            ViewMoreScreen(navController, textColor, backgroundColor, newsViewModel)
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
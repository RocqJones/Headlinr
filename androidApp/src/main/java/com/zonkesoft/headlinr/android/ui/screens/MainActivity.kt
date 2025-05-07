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
import com.zonkesoft.headlinr.android.models.Screen
import com.zonkesoft.headlinr.android.ui.theme.MyApplicationTheme

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
fun NavigationGraph() {
    val currentContext = LocalContext.current
    val navController = rememberNavController()

    NavHost(
        navController, startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController)
        }
    }
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
fun NavigationGraphPreview() {
    MyApplicationTheme {
        NavigationGraph()
    }
}
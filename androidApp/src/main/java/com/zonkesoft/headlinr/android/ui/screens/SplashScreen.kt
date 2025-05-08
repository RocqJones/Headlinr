package com.zonkesoft.headlinr.android.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zonkesoft.headlinr.android.R
import com.zonkesoft.headlinr.android.models.Screen
import com.zonkesoft.headlinr.android.ui.helpers.texts.BoldText
import com.zonkesoft.headlinr.android.ui.helpers.texts.MediumText
import com.zonkesoft.headlinr.android.ui.theme.MyApplicationTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    textColor: Color,
    backgroundColor: Color
) {

    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate(Screen.HomeScreen.route)
    }

    Box(
        modifier = Modifier.fillMaxSize().background(backgroundColor).padding(16.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.mipmap.logo),
                contentDescription = stringResource(R.string.icon),
                modifier = Modifier.size(width = 250.dp, height = 250.dp)
            )
        }

        Column(
            modifier = Modifier.align(Alignment.BottomStart).padding(bottom = 32.dp),
        ) {
            BoldText(
                text = stringResource(R.string.app_name),
                textColor = textColor,
                fontSize = 28.sp,
                textAlign = TextAlign.Start,
            )

            MediumText(
                text = stringResource(R.string.your_world_your_headlines_your_way),
                textColor = textColor,
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )
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
fun SplashScreenPreview() {
    MyApplicationTheme {
        SplashScreen(
            rememberNavController(),
            MaterialTheme.colorScheme.onBackground,
            MaterialTheme.colorScheme.background
        )
    }
}
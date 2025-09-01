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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zonkesoft.headlinr.android.R
import com.zonkesoft.headlinr.android.ui.common.spacers.SpacerCommon
import com.zonkesoft.headlinr.android.ui.common.texts.BoldText
import com.zonkesoft.headlinr.android.ui.common.texts.MediumText
import com.zonkesoft.headlinr.android.ui.navigation.Screen
import com.zonkesoft.headlinr.android.ui.theme.MyApplicationTheme
import com.zonkesoft.headlinr.presentation.vm.InterfaceViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.getViewModel

@Composable
fun SplashScreen(
    navController: NavHostController,
    textColor: Color,
    backgroundColor: Color,
    interfaceViewModel: InterfaceViewModel = getViewModel()
) {
    val screenContent = interfaceViewModel.splashScreenModel.collectAsState()

    LaunchedEffect(Unit) {
        delay(screenContent.value.delay)
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
                text = screenContent.value.header,
                textColor = textColor,
                fontSize = 28.sp,
                textAlign = TextAlign.Start,
            )

            MediumText(
                text = screenContent.value.subHeader,
                textColor = textColor,
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )

            SpacerCommon(16, isVertical = true)
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
            navController = rememberNavController(),
            MaterialTheme.colorScheme.onBackground,
            MaterialTheme.colorScheme.background,
            viewModel()
        )
    }
}
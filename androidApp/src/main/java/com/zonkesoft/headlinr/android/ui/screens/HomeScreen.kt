package com.zonkesoft.headlinr.android.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zonkesoft.headlinr.android.R
import com.zonkesoft.headlinr.android.ui.common.contents.HomeMainContent
import com.zonkesoft.headlinr.android.ui.common.contents.MenuContent
import com.zonkesoft.headlinr.android.ui.common.contents.ProfileContent
import com.zonkesoft.headlinr.android.ui.common.contents.TopAppBarContent
import com.zonkesoft.headlinr.android.ui.navigation.Screen
import com.zonkesoft.headlinr.android.ui.theme.MyApplicationTheme
import com.zonkesoft.headlinr.presentation.vm.InterfaceViewModel
import com.zonkesoft.headlinr.presentation.vm.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    textColor: Color,
    backgroundColor: Color,
    newsViewModel: NewsViewModel,
    interfaceViewModel: InterfaceViewModel
) {
    val sheetState = rememberModalBottomSheetState()
    var showMenuBottomSheet by remember { mutableStateOf(false) }
    var showProfileBottomSheet by remember { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .background(backgroundColor),
        topBar = {
            TopAppBarContent(
                title = stringResource(R.string.for_you),
                textColor = textColor,
                scrollBehavior = scrollBehavior,
                topBarColor = backgroundColor,
                onMenuClick = { showMenuBottomSheet = true },
                onSearchClicked = { navController.navigate(Screen.SearchScreen.route) },
                onProfileClicked = { showProfileBottomSheet = true }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(backgroundColor)
        ) {
            HomeMainContent(navController, textColor, interfaceViewModel, newsViewModel)
        }
    }

    when {
        showMenuBottomSheet -> {
            ModalBottomSheet(
                containerColor = backgroundColor,
                onDismissRequest = { showMenuBottomSheet = false },
                sheetState = sheetState
            ) {
                MenuContent(
                    textColor = textColor,
                    interfaceViewModel = interfaceViewModel,
                    onTopicClick = { topicTitle ->
                        showMenuBottomSheet = false
                        newsViewModel.getTopicsByQuery(topicTitle)
                        newsViewModel.setViewAllItems(
                            title = topicTitle, isTopics = true, list = emptyList()
                        )
                        navController.navigate(Screen.ViewAllScreen.route)
                    }
                )
            }
        }
    }

    when {
        showProfileBottomSheet -> {
            ModalBottomSheet(
                containerColor = backgroundColor,
                onDismissRequest = { showProfileBottomSheet = false },
                sheetState = sheetState
            ) {
                ProfileContent(textColor, interfaceViewModel)
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
fun HomeScreenPreview() {
    MyApplicationTheme {
        HomeScreen(
            rememberNavController(),
            MaterialTheme.colorScheme.onBackground,
            MaterialTheme.colorScheme.background,
            viewModel(),
            viewModel()
        )
    }
}
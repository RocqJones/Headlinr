package com.zonkesoft.headlinr.android.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
import com.zonkesoft.headlinr.android.ui.common.contents.MenuContent
import com.zonkesoft.headlinr.android.ui.common.contents.ProfileContent
import com.zonkesoft.headlinr.android.ui.common.contents.TopAppBarContent
import com.zonkesoft.headlinr.android.ui.common.spacers.SpacerCommon
import com.zonkesoft.headlinr.android.ui.common.texts.BoldText
import com.zonkesoft.headlinr.android.ui.common.texts.BoldTextWithIcon
import com.zonkesoft.headlinr.android.ui.common.texts.MediumText
import com.zonkesoft.headlinr.android.ui.common.texts.RegularText
import com.zonkesoft.headlinr.android.ui.theme.MyApplicationTheme
import com.zonkesoft.headlinr.android.ui.theme.fireColor
import com.zonkesoft.headlinr.android.ui.theme.linkColor
import com.zonkesoft.headlinr.data.vm.InterfaceViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    textColor: Color,
    backgroundColor: Color,
    interfaceViewModel: InterfaceViewModel = getViewModel()
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
                onSearchClicked = { /* */ },
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
            MainContent(navController, textColor, interfaceViewModel)
        }
    }

    when {
        showMenuBottomSheet -> {
            ModalBottomSheet(
                onDismissRequest = { showMenuBottomSheet = false },
                sheetState = sheetState
            ) {
                MenuContent(textColor, interfaceViewModel)
            }
        }
    }

    when {
        showProfileBottomSheet -> {
            ModalBottomSheet(
                onDismissRequest = { showProfileBottomSheet = false },
                sheetState = sheetState
            ) {
                ProfileContent(textColor, interfaceViewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    navController: NavHostController,
    textColor: Color,
    interfaceViewModel: InterfaceViewModel
) {
    val todayDate by interfaceViewModel.todayDate.collectAsState()
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        HorizontalDivider()
        SpacerCommon(size = 8, isVertical = true)

        RegularText(
            text = todayDate.orEmpty(),
            textColor = textColor,
            fontSize = 14.sp,
            textAlign = TextAlign.Start
        )

        SpacerCommon(size = 8, isVertical = true)

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BoldText(
                text = stringResource(R.string.top_stories),
                textColor = textColor,
                fontSize = 24.sp,
                textAlign = TextAlign.Start
            )

            MediumText(
                text = stringResource(R.string.see_all),
                textColor = linkColor,
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )
        }

        SpacerCommon(size = 16, isVertical = true)

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BoldTextWithIcon(
                text = stringResource(R.string.trending),
                textColor = textColor,
                fontSize = 24.sp,
                textAlign = TextAlign.Start,
                icon = painterResource(R.drawable.outline_local_fire_department_24),
                iconColor = fireColor,
            )

            MediumText(
                text = stringResource(R.string.see_all),
                textColor = linkColor,
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )
        }

        SpacerCommon(size = 16, isVertical = true)

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BoldText(
                text = stringResource(R.string.highlights),
                textColor = textColor,
                fontSize = 24.sp,
                textAlign = TextAlign.Start
            )

            MediumText(
                text = stringResource(R.string.see_all),
                textColor = linkColor,
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
fun HomeScreenPreview() {
    MyApplicationTheme {
        HomeScreen(rememberNavController(),
            MaterialTheme.colorScheme.onBackground,
            MaterialTheme.colorScheme.background,
            viewModel()
        )
    }
}
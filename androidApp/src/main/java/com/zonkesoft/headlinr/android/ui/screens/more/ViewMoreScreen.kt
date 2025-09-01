package com.zonkesoft.headlinr.android.ui.screens.more

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.zonkesoft.headlinr.android.R
import com.zonkesoft.headlinr.android.ui.common.cards.CurvedCardNoPadding
import com.zonkesoft.headlinr.android.ui.common.contents.WebViewBottomSheet
import com.zonkesoft.headlinr.android.ui.common.spacers.SpacerCommon
import com.zonkesoft.headlinr.android.ui.common.texts.BoldText
import com.zonkesoft.headlinr.android.ui.common.texts.MediumText
import com.zonkesoft.headlinr.android.ui.common.texts.RegularText
import com.zonkesoft.headlinr.android.ui.theme.linkColor
import com.zonkesoft.headlinr.presentation.vm.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewMoreScreen(
    navController: NavHostController,
    textColor: Color,
    backgroundColor: Color,
    newsViewModel: NewsViewModel
) {
    val mainModel = newsViewModel.articlesModel.collectAsState()
    val sheetState = rememberModalBottomSheetState()
    val showWebBottomSheet = remember { mutableStateOf(false) }

    LaunchedEffect(mainModel) {
        Log.d("ViewMoreScreen", "mainModel: ${mainModel.value}")
    }

    Box {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier.clickable { navController.popBackStack() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back_ios_new_24),
                    contentDescription = "Back",
                    tint = linkColor
                )

                SpacerCommon(size = 8, isHorizontal = true)

                MediumText(
                    text = stringResource(R.string.for_you),
                    textColor = linkColor,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start
                )
            }

            // Content for View More Screen
            SpacerCommon(size = 20, isVertical = true)

            BoldText(
                text = mainModel.value.title ?: stringResource(R.string.title),
                textColor = textColor,
                fontSize = 20.sp,
                textAlign = TextAlign.Start
            )

            SpacerCommon(size = 16, isVertical = true)

            MediumText(
                text = "By: ${mainModel.value.author ?: stringResource(R.string.source)}",
                textColor = textColor,
                fontSize = 14.sp,
                textAlign = TextAlign.Start
            )
            SpacerCommon(size = 4, isVertical = true)

            MediumText(
                text = mainModel.value.publishedAt ?: stringResource(R.string.dd_mm_yyyy),
                textColor = textColor,
                fontSize = 14.sp,
                textAlign = TextAlign.End
            )

            SpacerCommon(size = 8, isVertical = true)

            CurvedCardNoPadding {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    model = mainModel.value.urlToImage,
                    contentDescription = stringResource(R.string.img),
                    contentScale = ContentScale.FillBounds
                )
            }

            SpacerCommon(size = 16, isVertical = true)

            RegularText(
                text = "Source: ${mainModel.value.source?.name ?: stringResource(R.string.source)}",
                textColor = textColor,
                fontSize = 14.sp,
                textAlign = TextAlign.Start
            )

            SpacerCommon(size = 16, isVertical = true)

            MediumText(
                text = mainModel.value.description.orEmpty(),
                textColor = textColor,
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )

            SpacerCommon(size = 16, isVertical = true)

            RegularText(
                text = mainModel.value.content.orEmpty(),
                textColor = textColor,
                fontSize = 14.sp,
                textAlign = TextAlign.Start
            )

            SpacerCommon(size = 16, isVertical = true)

            BoldText(
                text = stringResource(R.string.read_more_at),
                textColor = linkColor,
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.clickable {
                    showWebBottomSheet.value = true
                }
            )

            SpacerCommon(size = 16, isVertical = true)
        }
    }

    if (showWebBottomSheet.value) {
        ModalBottomSheet(
            containerColor = backgroundColor,
            onDismissRequest = { showWebBottomSheet.value = false },
            sheetState = sheetState,
        ) {
            WebViewBottomSheet(
                url = mainModel.value.url.orEmpty(),
                textColor = textColor,
                backgroundColor = backgroundColor
            )
        }
    }
}
package com.zonkesoft.headlinr.android.ui.screens.more

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.zonkesoft.headlinr.android.R
import com.zonkesoft.headlinr.android.ui.common.contents.ErrorMessage
import com.zonkesoft.headlinr.android.ui.common.contents.TrendingContent
import com.zonkesoft.headlinr.android.ui.common.dialogs.LottieLoader
import com.zonkesoft.headlinr.android.ui.common.spacers.SpacerCommon
import com.zonkesoft.headlinr.android.ui.common.texts.MediumText
import com.zonkesoft.headlinr.android.ui.theme.linkColor
import com.zonkesoft.headlinr.presentation.state.TopicsUiState
import com.zonkesoft.headlinr.presentation.vm.NewsViewModel

@Composable
fun ViewAllScreen(
    navController: NavHostController,
    textColor: Color,
    newsViewModel: NewsViewModel
) {
    val title = newsViewModel.viewAllTitle.collectAsState().value
    val items = newsViewModel.viewAllItems.collectAsState().value
    val isTopics = newsViewModel.isTopics.collectAsState().value
    val topicsState = newsViewModel.topicsState.collectAsState()

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
                    text = title,
                    textColor = linkColor,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start
                )
            }

            SpacerCommon(size = 24, isVertical = true)

            when {
                isTopics -> {
                    topicsState.value.also {
                        when (it) {
                            is TopicsUiState.Loading -> {
                                when {
                                    it.loading -> LottieLoader(textColor = textColor)
                                }
                            }

                            is TopicsUiState.Error -> {
                                ErrorMessage(title = it.title, message = it.message, textColor = textColor)
                            }

                            is TopicsUiState.TopicsContent -> {
                                TrendingContent(
                                    trending = it.topicsResults,
                                    textColor = textColor,
                                    navController = navController,
                                    newsViewModel = newsViewModel
                                )
                            }
                        }
                    }
                }

                else -> {
                    TrendingContent(
                        trending = items,
                        textColor = textColor,
                        navController = navController,
                        newsViewModel = newsViewModel
                    )
                }
            }
        }
    }
}
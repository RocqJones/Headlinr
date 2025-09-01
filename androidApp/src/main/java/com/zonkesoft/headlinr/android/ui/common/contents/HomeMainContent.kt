package com.zonkesoft.headlinr.android.ui.common.contents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.zonkesoft.headlinr.android.R
import com.zonkesoft.headlinr.android.ui.common.dialogs.LottieLoader
import com.zonkesoft.headlinr.android.ui.common.spacers.SpacerCommon
import com.zonkesoft.headlinr.android.ui.common.texts.BoldText
import com.zonkesoft.headlinr.android.ui.common.texts.BoldTextWithIcon
import com.zonkesoft.headlinr.android.ui.common.texts.MediumText
import com.zonkesoft.headlinr.android.ui.common.texts.RegularText
import com.zonkesoft.headlinr.android.ui.navigation.Screen
import com.zonkesoft.headlinr.android.ui.theme.fireColor
import com.zonkesoft.headlinr.android.ui.theme.linkColor
import com.zonkesoft.headlinr.presentation.state.TopStoriesUiState
import com.zonkesoft.headlinr.presentation.state.TrendingUiState
import com.zonkesoft.headlinr.presentation.state.HighlightsUiState
import com.zonkesoft.headlinr.presentation.vm.InterfaceViewModel
import com.zonkesoft.headlinr.presentation.vm.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeMainContent(
    navController: NavHostController,
    textColor: Color,
    interfaceViewModel: InterfaceViewModel,
    newsViewModel: NewsViewModel
) {
    val todayDate by interfaceViewModel.todayDate.collectAsState()

    val topStoriesState = newsViewModel.topHeadlinesState.collectAsState()
    val highlightsState = newsViewModel.highlightsState.collectAsState()
    val trendingState = newsViewModel.trendingState.collectAsState()

    val trendingTitle = stringResource(R.string.trending)
    val highlightsTitle = stringResource(R.string.highlights)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        HorizontalDivider()
        SpacerCommon(size = 8, isVertical = true)

        RegularText(
            text = todayDate.orEmpty(),
            textColor = textColor,
            fontSize = 14.sp,
            textAlign = TextAlign.Start
        )

        SpacerCommon(size = 8, isVertical = true)

        SectionHeaderRow(
            title = stringResource(R.string.top_stories),
            textColor = textColor,
            subTitle = "",
        )

        SpacerCommon(size = 16, isVertical = true)

        topStoriesState.value.also {
            when (it) {
                is TopStoriesUiState.Loading -> {
                    when {
                        it.loading -> LottieLoader(textColor = textColor)
                    }
                }

                is TopStoriesUiState.Error -> {
                    ErrorMessage(title = it.title, message = it.message, textColor = textColor)
                }

                is TopStoriesUiState.TopContent -> {
                    TopStoriesContent(
                        topStories = it.topStories,
                        textColor = textColor,
                        navController = navController,
                        newsViewModel = newsViewModel
                    )
                }
            }
        }

        SpacerCommon(size = 24, isVertical = true)

        SectionHeaderRow(
            title = trendingTitle,
            subTitle = stringResource(R.string.see_all),
            textColor = textColor,
            icon = painterResource(R.drawable.outline_local_fire_department_24),
            iconColor = fireColor
        ) {
            newsViewModel.setViewAllItems(
                title = trendingTitle,
                list = trendingState.value.let {
                    when (it) {
                        is TrendingUiState.TrendingContent -> it.trendingResults
                        else -> emptyList()
                    }
                }
            )
            navController.navigate(Screen.ViewAllScreen.route)
        }

        SpacerCommon(size = 16, isVertical = true)

        trendingState.value.also {
            when (it) {
                is TrendingUiState.Loading -> {
                    when {
                        it.loading -> LottieLoader(textColor = textColor)
                    }
                }

                is TrendingUiState.Error -> {
                    ErrorMessage(title = it.title, message = it.message, textColor = textColor)
                }

                is TrendingUiState.TrendingContent -> {
                    TrendingContent(
                        trending = it.trendingResults.take(10),
                        textColor = textColor,
                        navController = navController,
                        newsViewModel = newsViewModel
                    )
                }
            }
        }

        SpacerCommon(size = 24, isVertical = true)

        SectionHeaderRow(
            title = highlightsTitle,
            subTitle = stringResource(R.string.see_all),
            textColor = textColor
        ) {
            newsViewModel.setViewAllItems(
                title = highlightsTitle,
                list = highlightsState.value.let {
                    when (it) {
                        is HighlightsUiState.HighlightsContent -> it.highlightResults
                        else -> emptyList()
                    }
                }
            )
            navController.navigate(Screen.ViewAllScreen.route)
        }

        SpacerCommon(size = 16, isVertical = true)

        highlightsState.value.also {
            when (it) {
                is HighlightsUiState.Loading -> {
                    when {
                        it.loading -> LottieLoader(textColor = textColor)
                    }
                }

                is HighlightsUiState.Error -> {
                    ErrorMessage(title = it.title, message = it.message, textColor = textColor)
                }

                is HighlightsUiState.HighlightsContent -> {
                    HighlightsContent(
                        highlights = it.highlightResults.take(10),
                        textColor = textColor,
                        navController = navController,
                        newsViewModel = newsViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun SectionHeaderRow(
    title: String,
    subTitle: String,
    textColor: Color,
    fontSize: TextUnit = 24.sp,
    icon: Painter? = null,
    iconColor: Color = Color.Unspecified,
    onActionClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = onActionClick != null) {
                onActionClick?.invoke()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        when {
            icon != null -> {
                BoldTextWithIcon(
                    text = title,
                    textColor = textColor,
                    fontSize = fontSize,
                    textAlign = TextAlign.Start,
                    icon = icon,
                    iconColor = iconColor
                )
            }
            else -> {
                BoldText(
                    text = title,
                    textColor = textColor,
                    fontSize = fontSize,
                    textAlign = TextAlign.Start
                )
            }
        }

        MediumText(
            text = subTitle,
            textColor = linkColor,
            fontSize = 16.sp,
            textAlign = TextAlign.Start
        )
    }
}
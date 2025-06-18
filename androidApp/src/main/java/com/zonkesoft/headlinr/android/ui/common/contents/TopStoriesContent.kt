package com.zonkesoft.headlinr.android.ui.common.contents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.zonkesoft.headlinr.android.R
import com.zonkesoft.headlinr.android.ui.common.cards.CurvedCardNoPadding
import com.zonkesoft.headlinr.android.ui.common.spacers.SpacerCommon
import com.zonkesoft.headlinr.android.ui.common.texts.BoldText
import com.zonkesoft.headlinr.android.ui.common.texts.MediumText
import com.zonkesoft.headlinr.android.ui.common.texts.RegularText
import com.zonkesoft.headlinr.android.ui.navigation.Screen
import com.zonkesoft.headlinr.data.models.ArticlesModel
import com.zonkesoft.headlinr.presentation.vm.NewsViewModel

@Composable
fun TopStoriesContent(
    topStories: List<ArticlesModel>,
    textColor: Color,
    navController: NavHostController,
    newsViewModel: NewsViewModel
) {
    val listState = rememberLazyListState()
    val currentIndex = listState.firstVisibleItemIndex

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyRow(
            state = listState,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(topStories) { _, item ->
                Column(
                    modifier = Modifier
                        .width(300.dp)
                        .clickable(
                            onClick = {
                                newsViewModel.setArticlesModel(item)
                                navController.navigate(Screen.ViewMoreScreen.route)
                            }
                        )
                ) {
                    CurvedCardNoPadding {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            model = item.urlToImage,
                            contentDescription = stringResource(R.string.img),
                            contentScale = ContentScale.FillBounds
                        )
                    }

                    SpacerCommon(size = 8, isVertical = true)
                    RegularText(
                        text = item.source?.name ?: "Source",
                        textColor = textColor,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Start
                    )
                    SpacerCommon(size = 2, isVertical = true)
                    BoldText(
                        text = item.title ?: "Title",
                        textColor = textColor,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start
                    )
                    SpacerCommon(size = 2, isVertical = true)
                    MediumText(
                        text = item.publishedAt ?: "DD/MM/YYYY HH:MM",
                        textColor = textColor,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }

        SpacerCommon(size = 12, isVertical = true)

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(topStories.size) { index ->
                Box(
                    modifier = Modifier
                        .size(
                            when (index) {
                                currentIndex -> 12.dp
                                else -> 8.dp
                            }
                        )
                        .clip(CircleShape)
                        .background(
                            when (index) {
                                currentIndex -> textColor
                                else -> Color.LightGray
                            }
                        )
                        .padding(2.dp)
                )
                when {
                    index != topStories.lastIndex -> {
                        SpacerCommon(size = 6, isHorizontal = true)
                    }
                }
            }
        }
    }
}
package com.zonkesoft.headlinr.android.ui.common.contents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun HighlightsContent(
    highlights: List<ArticlesModel>,
    textColor: Color,
    navController: NavHostController,
    newsViewModel: NewsViewModel
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        highlights.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        newsViewModel.setArticlesModel(item)
                        navController.navigate(Screen.ViewMoreScreen.route)
                    }
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.Top
            ) {
                // Text content - 80% width
                Column(modifier = Modifier.weight(0.8f)) {
                    BoldText(
                        text = item.title ?: stringResource(R.string.title),
                        textColor = textColor,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start
                    )

                    MediumText(
                        text = item.description ?: stringResource(R.string.description),
                        textColor = textColor,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start
                    )

                    SpacerCommon(size = 4, isVertical = true)

                    RegularText(
                        text = "By: ${item.author ?: stringResource(R.string.author)}",
                        textColor = textColor,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Start
                    )

                    RegularText(
                        text = item.publishedAt ?: stringResource(R.string.dd_mm_yyyy),
                        textColor = textColor,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Start
                    )

                    RegularText(
                        text = "Source: ${item.source?.name ?: stringResource(R.string.source)}",
                        textColor = textColor,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Start
                    )
                }

                // Image content - 20% width
                CurvedCardNoPadding {
                    AsyncImage(
                        modifier = Modifier
                            .width(80.dp)
                            .height(80.dp),
                        model = item.urlToImage,
                        contentDescription = stringResource(R.string.img),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}
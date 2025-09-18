package com.zonkesoft.headlinr.android.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.zonkesoft.headlinr.android.R
import com.zonkesoft.headlinr.android.ui.common.contents.ErrorMessage
import com.zonkesoft.headlinr.android.ui.common.contents.TrendingContent
import com.zonkesoft.headlinr.android.ui.common.dialogs.LottieLoader
import com.zonkesoft.headlinr.android.ui.common.spacers.SpacerCommon
import com.zonkesoft.headlinr.android.ui.common.texts.BoldText
import com.zonkesoft.headlinr.android.ui.common.texts.MediumText
import com.zonkesoft.headlinr.android.ui.common.texts.RegularText
import com.zonkesoft.headlinr.presentation.state.SearchUiState
import com.zonkesoft.headlinr.presentation.vm.NewsViewModel
import com.zonkesoft.headlinr.presentation.vm.SearchViewModel

@Composable
fun SearchScreen(
    navController: NavHostController,
    textColor: Color,
    searchViewModel: SearchViewModel,
    newsViewModel: NewsViewModel
) {
    val searchQuery by searchViewModel.searchQuery.collectAsState()
    val searchState by searchViewModel.searchState.collectAsState()
    val recentSearches by searchViewModel.recentSearches.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back_ios_new_24),
                    contentDescription = stringResource(id = R.string.back),
                    tint = textColor
                )
            }
            TextField(
                value = searchQuery,
                onValueChange = { searchViewModel.setSearchQuery(it) },
                placeholder = {
                    MediumText(
                        text = stringResource(R.string.feature_search_title),
                        textColor = textColor
                    )
                },
                singleLine = true,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        if (searchQuery.isNotBlank()) {
                            searchViewModel.getResultsByQuery(searchQuery)
                            keyboardController?.hide()
                        }
                    }
                ),
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchViewModel.setSearchQuery("") }) {
                            Icon(
                                painter = painterResource(android.R.drawable.ic_menu_close_clear_cancel),
                                contentDescription = stringResource(id = R.string.feature_search_clear_search_text_content_desc),
                                tint = textColor
                            )
                        }
                    }
                }
            )
        }

        SpacerCommon(size = 16, isVertical = true)

        when {
            searchQuery.isBlank() && recentSearches.isNotEmpty() -> {
                BoldText(
                    text = stringResource(R.string.feature_search_recent_searches),
                    textColor = textColor,
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
                SpacerCommon(size = 8, isVertical = true)
                Column {
                    recentSearches.forEach { recent ->
                        RegularText(
                            text = recent,
                            textColor = textColor,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    searchViewModel.setSearchQuery(recent)
                                    searchViewModel.getResultsByQuery(recent)
                                }
                                .padding(vertical = 8.dp)
                        )
                    }
                    SpacerCommon(size = 8, isVertical = true)
                    MediumText(
                        text = stringResource(R.string.feature_search_clear_recent_searches_content_desc),
                        textColor = Color.Red,
                        modifier = Modifier
                            .clickable { searchViewModel.clearRecentSearches() }
                            .padding(vertical = 8.dp)
                    )
                }
            }

            else -> {
                when (searchState) {
                    is SearchUiState.Loading -> {
                        if ((searchState as SearchUiState.Loading).loading) {
                            LottieLoader(textColor = textColor)
                        }
                    }

                    is SearchUiState.Error -> {
                        val err = searchState as SearchUiState.Error
                        ErrorMessage(
                            title = err.title,
                            message = err.message,
                            textColor = textColor
                        )
                    }

                    is SearchUiState.SearchContent -> {
                        val results = (searchState as SearchUiState.SearchContent).searchResults

                        MediumText(
                            text = "Found ${results.size} results for \"$searchQuery\"",
                            textColor = textColor,
                            textAlign = TextAlign.Start
                        )

                        SpacerCommon(size = 16, isVertical = true)

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState())
                        ) {
                            TrendingContent(
                                trending = results,
                                textColor = textColor,
                                navController = navController,
                                newsViewModel = newsViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}

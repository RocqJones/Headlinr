package com.zonkesoft.headlinr.presentation.state

import com.zonkesoft.headlinr.data.models.ArticlesModel

sealed class TopStoriesUiState {
    data class Loading(val loading: Boolean = false) : TopStoriesUiState()
    data class Error(
        val title: String = "Something went wrong!",
        val message: Any,
    ) : TopStoriesUiState()
    data class TopContent(
        val topStories: List<ArticlesModel> = listOf()
    ) : TopStoriesUiState()
}

sealed class HighlightsUiState {
    data class Loading(val loading: Boolean = false) : HighlightsUiState()
    data class Error(
        val title: String = "Something went wrong!",
        val message: Any,
    ) : HighlightsUiState()
    data class HighlightsContent(
        val searchResults: List<ArticlesModel> = listOf(),
    ) : HighlightsUiState()
}

sealed class SearchUiState {
    data class Loading(val loading: Boolean = false) : SearchUiState()
    data class Error(
        val title: String = "Something went wrong!",
        val message: Any,
    ) : SearchUiState()
    data class SearchContent(
        val searchResults: List<ArticlesModel> = listOf(),
    ) : SearchUiState()
}
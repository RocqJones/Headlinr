package com.zonkesoft.headlinr.presentation.state

import com.zonkesoft.headlinr.data.models.ArticlesModel

sealed class TopStoriesUiState {
    data class Loading(val loading: Boolean = false) : TopStoriesUiState()
    data class Error(
        val title: String = "Something went wrong!",
        val message: String
    ) : TopStoriesUiState()
    data class TopContent(
        val topStories: List<ArticlesModel> = listOf()
    ) : TopStoriesUiState()
}

sealed class HighlightsUiState {
    data class Loading(val loading: Boolean = false) : HighlightsUiState()
    data class Error(
        val title: String = "Something went wrong!",
        val message: String
    ) : HighlightsUiState()
    data class HighlightsContent(
        val highlightResults: List<ArticlesModel> = listOf(),
    ) : HighlightsUiState()
}

sealed class TrendingUiState {
    data class Loading(val loading: Boolean = false) : TrendingUiState()
    data class Error(
        val title: String = "Something went wrong!",
        val message: String
    ) : TrendingUiState()
    data class TrendingContent(
        val trendingResults: List<ArticlesModel> = listOf(),
    ) : TrendingUiState()
}

sealed class SearchUiState {
    data class Loading(val loading: Boolean = false) : SearchUiState()
    data class Error(
        val title: String = "Something went wrong!",
        val message: String
    ) : SearchUiState()
    data class SearchContent(
        val searchResults: List<ArticlesModel> = listOf(),
    ) : SearchUiState()
}

sealed class TopicsUiState {
    data class Loading(val loading: Boolean = false) : TopicsUiState()
    data class Error(
        val title: String = "Something went wrong!",
        val message: String
    ) : TopicsUiState()
    data class TopicsContent(
        val topicsResults: List<ArticlesModel> = listOf(),
    ) : TopicsUiState()
}
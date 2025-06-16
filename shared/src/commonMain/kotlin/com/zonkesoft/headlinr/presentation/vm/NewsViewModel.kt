package com.zonkesoft.headlinr.presentation.vm

import com.zonkesoft.headlinr.BaseViewModel
import com.zonkesoft.headlinr.data.repository.NewsRepository
import com.zonkesoft.headlinr.presentation.state.TopStoriesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : BaseViewModel() {

    private val _topHeadlinesState : MutableStateFlow<TopStoriesUiState> = MutableStateFlow(
        TopStoriesUiState.Loading(loading = true)
    )
    val topHeadlinesState = _topHeadlinesState

    init {
        getTopHeadlines()
    }

    fun getTopHeadlines(forceFetch: Boolean = false) {
        scope.launch {
            _topHeadlinesState.emit(
                TopStoriesUiState.Loading(loading = true)
            )
            try {
                val response = repository.getTopHeadlines(forceFetch)
                when {
                    response.status == "ok" && response.totalResults > 0 -> {
                        _topHeadlinesState.emit(
                            TopStoriesUiState.TopContent(topStories = response.articles ?: listOf())
                        )
                    }
                    else -> {
                        _topHeadlinesState.emit(
                            TopStoriesUiState.Error(
                                title = "Error fetching top headlines",
                                message = response.status ?: "Unknown error"
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                _topHeadlinesState.emit(
                    TopStoriesUiState.Error(
                        title = "Exception while fetching top headlines",
                        message = e.message ?: "Unknown error"
                    )
                )
            }
        }
    }
}
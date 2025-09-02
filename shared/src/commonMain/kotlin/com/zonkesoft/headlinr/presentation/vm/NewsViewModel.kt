package com.zonkesoft.headlinr.presentation.vm

import com.zonkesoft.headlinr.BaseViewModel
import com.zonkesoft.headlinr.data.models.ArticlesModel
import com.zonkesoft.headlinr.data.repository.NewsRepository
import com.zonkesoft.headlinr.presentation.state.TopStoriesUiState
import com.zonkesoft.headlinr.presentation.state.HighlightsUiState
import com.zonkesoft.headlinr.presentation.state.SearchUiState
import com.zonkesoft.headlinr.presentation.state.TrendingUiState
import com.zonkesoft.headlinr.utils.HelperUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : BaseViewModel() {

    private val _topHeadlinesState: MutableStateFlow<TopStoriesUiState> =
        MutableStateFlow(TopStoriesUiState.Loading(loading = true))
    val topHeadlinesState = _topHeadlinesState

    private val _highlightsState: MutableStateFlow<HighlightsUiState> =
        MutableStateFlow(HighlightsUiState.Loading(loading = true))
    val highlightsState = _highlightsState

    private val _trendingState: MutableStateFlow<TrendingUiState> =
        MutableStateFlow(TrendingUiState.Loading(loading = true))
    val trendingState = _trendingState

    private val _searchState: MutableStateFlow<SearchUiState> =
        MutableStateFlow(SearchUiState.Loading(loading = true))
    val searchState = _searchState

    private val _articlesModel = MutableStateFlow(ArticlesModel())
    val articlesModel: StateFlow<ArticlesModel> get() = _articlesModel

    private val _viewAllTitle = MutableStateFlow("")
    val viewAllTitle: StateFlow<String> get() = _viewAllTitle

    private val _viewAllItems = MutableStateFlow(listOf<ArticlesModel>())
    val viewAllItems: StateFlow<List<ArticlesModel>> get() = _viewAllItems

    init {
        getTopHeadlines()
        getHighlights()
        getTrending()
        // TODO: Call getSearchResults() here
    }

    fun getTopHeadlines(forceFetch: Boolean = false) {
        scope.launch {
            _topHeadlinesState.emit(TopStoriesUiState.Loading(loading = true))
            try {
                val response = repository.getTopHeadlines(forceFetch)
                when {
                    response.status == "ok" && response.totalResults > 0 -> {
                        val updatedArticles = response.articles?.map {
                            it.copy(publishedAt = HelperUtil.convertDate(it.publishedAt ?: ""))
                        } ?: listOf()

                        _topHeadlinesState.emit(TopStoriesUiState.Loading(loading = false))
                        _topHeadlinesState.emit(
                            TopStoriesUiState.TopContent(topStories = updatedArticles)
                        )
                    }

                    else -> {
                        _topHeadlinesState.emit(TopStoriesUiState.Loading(loading = false))
                        _topHeadlinesState.emit(
                            TopStoriesUiState.Error(
                                title = "Error fetching top stories",
                                message = "${response.status}, totalResults:${response.totalResults}"
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                _topHeadlinesState.emit(TopStoriesUiState.Loading(loading = false))
                _topHeadlinesState.emit(
                    TopStoriesUiState.Error(
                        title = "Exception while fetching top stories",
                        message = e.message ?: "Unknown error"
                    )
                )
            }
        }
    }

    fun getHighlights(forceFetch: Boolean = false) {
        scope.launch {
            _highlightsState.emit(HighlightsUiState.Loading(loading = true))
            try {
                val response = repository.getHighlights(forceFetch)
                when {
                    response.status == "ok" && response.totalResults > 0 -> {
                        val updatedArticles = response.articles?.map {
                            it.copy(publishedAt = HelperUtil.convertDate(it.publishedAt ?: ""))
                        } ?: listOf()

                        _highlightsState.emit(HighlightsUiState.Loading(loading = false))
                        _highlightsState.emit(
                            HighlightsUiState.HighlightsContent(highlightResults = updatedArticles)
                        )
                    }

                    else -> {
                        _highlightsState.emit(HighlightsUiState.Loading(loading = false))
                        _highlightsState.emit(
                            HighlightsUiState.Error(
                                title = "Error fetching highlights",
                                message = response.status ?: "Unknown error"
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                _highlightsState.emit(HighlightsUiState.Loading(loading = false))
                _highlightsState.emit(
                    HighlightsUiState.Error(
                        title = "Exception while fetching highlights",
                        message = e.message ?: "Unknown error"
                    )
                )
            }
        }
    }

    fun getTrendingByQuery(query: String = "trending") {
        scope.launch {
            _trendingState.emit(TrendingUiState.Loading(loading = true))
            try {
                val response = repository.getEverythingWithQuery(query)
                when {
                    response.status == "ok" && response.totalResults > 0 -> {
                        val updatedArticles = response.articles?.map {
                            it.copy(publishedAt = HelperUtil.convertDate(it.publishedAt ?: ""))
                        } ?: listOf()

                        _trendingState.emit(TrendingUiState.Loading(loading = false))
                        _trendingState.emit(
                            TrendingUiState.TrendingContent(trendingResults = updatedArticles)
                        )
                    }

                    else -> {
                        _trendingState.emit(TrendingUiState.Loading(loading = false))
                        _trendingState.emit(
                            TrendingUiState.Error(
                                title = "Error fetching trending",
                                message = response.status ?: "Unknown error"
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                _trendingState.emit(TrendingUiState.Loading(loading = false))
                _trendingState.emit(
                    TrendingUiState.Error(
                        title = "Exception while fetching trending",
                        message = e.message ?: "Unknown error"
                    )
                )
            }
        }
    }

    fun setArticlesModel(articlesModel: ArticlesModel) {
        scope.launch { _articlesModel.emit(articlesModel) }
    }

    fun setViewAllItems(title : String, list: List<ArticlesModel>) {
        scope.launch {
            _viewAllTitle.emit(title)
            _viewAllItems.emit(list)
        }
    }
}
package com.zonkesoft.headlinr.presentation.vm

import com.zonkesoft.headlinr.BaseViewModel
import com.zonkesoft.headlinr.data.repository.NewsRepository
import com.zonkesoft.headlinr.presentation.state.SearchUiState
import com.zonkesoft.headlinr.utils.Constants
import com.zonkesoft.headlinr.utils.HelperUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: NewsRepository) : BaseViewModel() {

    private val _searchState: MutableStateFlow<SearchUiState> =
        MutableStateFlow(SearchUiState.Loading(loading = true))
    val searchState = _searchState

    private val _recentSearches = MutableStateFlow<List<String>>(emptyList())
    val recentSearches = _recentSearches
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery

    fun setSearchQuery(query: String) {
        scope.launch { _searchQuery.emit(query) }
    }

    fun clearRecentSearches() {
        scope.launch { _recentSearches.emit(emptyList()) }
    }

    fun getResultsByQuery(query: String?) {
        val trimmedQuery = query?.trim().orEmpty()
        scope.launch {
            _searchState.emit(SearchUiState.Loading(loading = true))
            try {
                val response = repository.getEverythingWithQuery(trimmedQuery.ifBlank { Constants.trending })
                when {
                    response.status == "ok" && response.totalResults > 0 -> {
                        val updatedArticles = response.articles?.map {
                            it.copy(publishedAt = HelperUtil.convertDate(it.publishedAt ?: ""))
                        } ?: listOf()

                        _searchState.emit(SearchUiState.Loading(loading = false))
                        _searchState.emit(
                            SearchUiState.SearchContent(searchResults = updatedArticles)
                        )
                        // Add to recent searches if not blank and not already present
                        if (trimmedQuery.isNotBlank()) {
                            val current = _recentSearches.value.toMutableList()
                            current.remove(trimmedQuery)
                            current.add(0, trimmedQuery)
                            if (current.size > 10) current.removeLast()
                            _recentSearches.emit(current)
                        }
                    }

                    else -> {
                        _searchState.emit(SearchUiState.Loading(loading = false))
                        _searchState.emit(
                            SearchUiState.Error(
                                title = "Error fetching topic",
                                message = response.status ?: "Unknown error"
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                _searchState.emit(SearchUiState.Loading(loading = false))
                _searchState.emit(
                    SearchUiState.Error(
                        title = "Exception while fetching topic",
                        message = e.message ?: "Unknown error"
                    )
                )
            }
        }
    }
}
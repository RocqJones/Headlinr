package com.zonkesoft.headlinr.data.repository

import com.zonkesoft.headlinr.data.models.TopHeadlineResponseModel
import com.zonkesoft.headlinr.network.service.NewsService

class NewsRepository(private val service: NewsService) {

    suspend fun getTopHeadlines(forceRefresh: Boolean = false): TopHeadlineResponseModel {
        try {
            val topHeadlines = when {
                forceRefresh -> { service.getTopHeadlines() }
                else -> {
                    // Logic to fetch from local storage or cache
                    service.getTopHeadlines()
                }
            }
            return topHeadlines
        } catch (e: Exception) {
            return TopHeadlineResponseModel(
                status = "Error: ${e.message}",
                totalResults = 0,
                articles = emptyList()
            )
        }
    }

    suspend fun getHighlights(forceRefresh: Boolean = false): TopHeadlineResponseModel {
        try {
            val highlights = when {
                forceRefresh -> { service.getHighlights() }
                else -> {
                    // Logic to fetch from local storage or cache
                    service.getHighlights()
                }
            }
            return highlights
        } catch (e: Exception) {
            return TopHeadlineResponseModel(
                status = "Error: ${e.message}",
                totalResults = 0,
                articles = emptyList()
            )
        }
    }

    suspend fun getEverythingWithQuery(
        query: String
    ): TopHeadlineResponseModel {
        return try {
            service.getEverythingWithQuery(query = query)
        } catch (e: Exception) {
            TopHeadlineResponseModel(
                status = "Error: ${e.message}",
                totalResults = 0,
                articles = emptyList()
            )
        }
    }
}
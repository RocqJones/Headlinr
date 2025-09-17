package com.zonkesoft.headlinr.data.repository

import com.zonkesoft.headlinr.data.models.ApiResponseModel
import com.zonkesoft.headlinr.network.service.NewsService
import com.zonkesoft.headlinr.utils.Constants

class NewsRepository(private val service: NewsService) {

    suspend fun getTopHeadlines(forceRefresh: Boolean = false): ApiResponseModel {
        try {
            val topHeadlines = when {
                forceRefresh -> {
                    service.getTopHeadlines()
                }

                else -> {
                    // Logic to fetch from local storage or cache
                    service.getTopHeadlines()
                }
            }
            return topHeadlines
        } catch (e: Exception) {
            return ApiResponseModel(
                status = "Error: ${e.message}",
                totalResults = 0,
                articles = emptyList()
            )
        }
    }

    suspend fun getHighlights(forceRefresh: Boolean = false): ApiResponseModel {
        try {
            val highlights = when {
                forceRefresh -> {
                    service.getEverythingWithQuery(query = Constants.all)
                }

                else -> {
                    // Logic to fetch from local storage or cache
                    service.getEverythingWithQuery(query = Constants.all)
                }
            }
            return highlights
        } catch (e: Exception) {
            return ApiResponseModel(
                status = "Error: ${e.message}",
                totalResults = 0,
                articles = emptyList()
            )
        }
    }

    suspend fun getEverythingWithQuery(
        query: String
    ): ApiResponseModel {
        return try {
            service.getEverythingWithQuery(query = query)
        } catch (e: Exception) {
            ApiResponseModel(
                status = "Error: ${e.message}",
                totalResults = 0,
                articles = emptyList()
            )
        }
    }
}
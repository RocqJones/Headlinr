package com.zonkesoft.headlinr.network.service

import com.zonkesoft.headlinr.data.models.TopHeadlineResponseModel
import com.zonkesoft.headlinr.utils.Constants
import com.zonkesoft.headlinr.utils.HelperUtil
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class NewsService(private val httpClient: HttpClient) {

    suspend fun getTopHeadlines(): TopHeadlineResponseModel {
        val response : TopHeadlineResponseModel = httpClient.get(
            "${Constants.baseUrl}${Constants.topHeadlinesEndpoint}?country=${HelperUtil.getDefaultCountryCode()}&apiKey=${Constants.apiKey}"
        ).body()
        return response
    }

    suspend fun getHighlights(): TopHeadlineResponseModel {
        val response : TopHeadlineResponseModel = httpClient.get(
            "${Constants.baseUrl}${Constants.everythingEndpoint}?q=all&apiKey=${Constants.apiKey}"
        ).body()
        return response
    }

    suspend fun getEverythingWithQuery(
        query: String
    ): TopHeadlineResponseModel {
        val response : TopHeadlineResponseModel = httpClient.get(
            "${Constants.baseUrl}${Constants.everythingEndpoint}?q=$query&apiKey=${Constants.apiKey}"
        ).body()
        return response
    }
}
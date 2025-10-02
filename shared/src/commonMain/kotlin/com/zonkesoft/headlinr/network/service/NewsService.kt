package com.zonkesoft.headlinr.network.service

import com.zonkesoft.headlinr.data.models.ApiResponseModel
import com.zonkesoft.headlinr.utils.Constants
import com.zonkesoft.headlinr.utils.HelperUtil
import com.zonkesoft.headlinr.utils.ApiLogger
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class NewsService(private val httpClient: HttpClient) {

    suspend fun getTopHeadlines(): ApiResponseModel {
        val url = "${Constants.baseUrl}${Constants.topHeadlinesEndpoint}?country=${HelperUtil.getDefaultCountryCode()}&apiKey=${Constants.apiKey}"
        ApiLogger.logUrl(url)

        val response: ApiResponseModel = httpClient.get(url).body()
        return response
    }

    suspend fun getEverythingWithQuery(
        query: String
    ): ApiResponseModel {
        val url = "${Constants.baseUrl}${Constants.everythingEndpoint}?q=$query&apiKey=${Constants.apiKey}"
        ApiLogger.logUrl(url)

        val response: ApiResponseModel = httpClient.get(url).body()
        return response
    }
}
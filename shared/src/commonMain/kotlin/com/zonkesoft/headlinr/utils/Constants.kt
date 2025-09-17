package com.zonkesoft.headlinr.utils

import com.zonkesoft.headlinr.PlatformImplementation

object Constants {
    internal const val baseUrl = "https://newsapi.org/v2"
    internal const val topHeadlinesEndpoint = "/top-headlines"
    internal const val everythingEndpoint = "/everything"
    internal const val all = "all"
    internal const val trending = "trending"
    internal val apiKey = PlatformImplementation().getApiKey()
}
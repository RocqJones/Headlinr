package com.zonkesoft.headlinr.utils

import com.zonkesoft.headlinr.PlatformImplementation

object Constants {
    internal const val baseUrl = "https://newsapi.org/v2/top-headlines"
    internal val apiKey = PlatformImplementation().getApiKey()
}
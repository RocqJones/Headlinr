package com.zonkesoft.headlinr

class PlatformImplementation {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }

    fun getApiKey(): String {
        return platform.apiKey
    }
}
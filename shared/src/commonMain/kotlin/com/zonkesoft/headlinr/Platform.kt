package com.zonkesoft.headlinr

interface Platform {
    val name: String
    val apiKey: String
}

expect fun getPlatform(): Platform
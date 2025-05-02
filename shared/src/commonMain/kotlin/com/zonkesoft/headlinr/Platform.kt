package com.zonkesoft.headlinr

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
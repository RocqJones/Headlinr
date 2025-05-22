package com.zonkesoft.headlinr.data.models

data class SplashScreenModel(
    val header: String,
    val subHeader: String,
    val delay: Long,
)

data class MenuItem(
    val icon: Int? = null,
    val title: String? = null,
)

data class Topics(
    val icon: Int? = null,
    val title: String? = null,
)
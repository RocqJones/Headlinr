package com.zonkesoft.headlinr.data.models

data class SplashScreenModel(
    val header: String? = null,
    val subHeader: String? = null,
    val delay: Long? = null,
)

data class MenuItem(
    val icon: Int? = null,
    val title: String? = null,
)

data class Topics(
    val icon: Int? = null,
    val title: String? = null,
)
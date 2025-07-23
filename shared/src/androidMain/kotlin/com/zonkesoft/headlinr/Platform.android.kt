package com.zonkesoft.headlinr

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
    override val apiKey: String = BuildConfig.API_KEY
}

actual fun getPlatform(): Platform = AndroidPlatform()
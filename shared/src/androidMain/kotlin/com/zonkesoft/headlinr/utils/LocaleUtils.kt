package com.zonkesoft.headlinr.utils

import java.util.Locale

actual fun getPlatformCountryCode(): String {
    return Locale.getDefault().country
}
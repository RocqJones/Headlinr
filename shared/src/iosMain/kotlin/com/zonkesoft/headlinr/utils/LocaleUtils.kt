package com.zonkesoft.headlinr.utils

import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.countryCode

actual fun getPlatformCountryCode(): String {
    return NSLocale.currentLocale.countryCode ?: "US"
}
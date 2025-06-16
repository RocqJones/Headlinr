package com.zonkesoft.headlinr.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object HelperUtil {

    fun getTodayDate() : String {
        try {
            val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            val dayOfWeek = now.date.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() } // e.g., "Monday"
            val day = now.date.dayOfMonth.toString().padStart(2, '0') // e.g., "20"
            val month = now.date.month.name.lowercase().replaceFirstChar { it.uppercase() } // e.g., "October"
            val year = now.date.year
            return "$dayOfWeek, $day $month $year"
        } catch (e: Exception) {
            Error("Error setting up today's date: ${e.message}")
            return "Unknown Date"
        }
    }

    fun getDefaultCountryCode(): String {
        return try {
            getPlatformCountryCode()
        } catch (e: Exception) {
            println("Error getting default country code: ${e.message}")
            "US" // fallback country code
        }
    }
}
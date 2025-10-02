package com.zonkesoft.headlinr.utils

/**
 * A multiplatform logging utility for structured logging across the application
 */
object Logger {

    enum class LogLevel(val tag: String) {
        DEBUG("🐛 DEBUG"),
        INFO("ℹ️ INFO"),
        WARNING("⚠️ WARNING"),
        ERROR("❌ ERROR"),
        API("🌐 API")
    }

    private var isLoggingEnabled = true

    fun enableLogging(enabled: Boolean) {
        isLoggingEnabled = enabled
    }

    fun debug(tag: String, message: String) {
        log(LogLevel.DEBUG, tag, message)
    }

    fun info(tag: String, message: String) {
        log(LogLevel.INFO, tag, message)
    }

    fun warning(tag: String, message: String) {
        log(LogLevel.WARNING, tag, message)
    }

    fun error(tag: String, message: String, throwable: Throwable? = null) {
        val errorMessage = if (throwable != null) {
            "$message - ${throwable.message}"
        } else {
            message
        }
        log(LogLevel.ERROR, tag, errorMessage)
    }

    fun api(tag: String, message: String) {
        log(LogLevel.API, tag, message)
    }

    private fun log(level: LogLevel, tag: String, message: String) {
        if (!isLoggingEnabled) return
        println("${level.tag} [$tag] $message")
    }
}

/**
 * Extension functions for easier API logging
 */
object ApiLogger {

    fun logRequest(endpoint: String, params: Map<String, Any> = emptyMap()) {
        val paramString = if (params.isNotEmpty()) {
            params.entries.joinToString(", ") { "${it.key}=${it.value}" }
        } else {
            "no parameters"
        }
        Logger.api("REQUEST", "🚀 $endpoint | $paramString")
    }

    fun logResponse(endpoint: String, status: String, totalResults: Int, duration: Long? = null) {
        val durationText = duration?.let { " (${it}ms)" } ?: ""
        Logger.api("RESPONSE", "✅ $endpoint | status=$status | results=$totalResults$durationText")
    }

    fun logError(endpoint: String, error: String, duration: Long? = null) {
        val durationText = duration?.let { " (${it}ms)" } ?: ""
        Logger.api("ERROR", "❌ $endpoint | $error$durationText")
    }

    fun logUrl(url: String) {
        Logger.api("URL", "🔗 $url")
    }
}

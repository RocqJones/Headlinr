package com.zonkesoft.headlinr.android.config

import android.app.Application
import android.util.Log
import com.zonkesoft.headlinr.android.di.viewModelsModule
import com.zonkesoft.headlinr.di.sharedKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class AppConfig : Application() {
    private val tag = "AppConfig"

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    /**
     * Initialize Koin for dependency injection
     * This function sets up Koin with the Android context and loads the required modules.
     */
    private fun initKoin() {
        try {
            val module = sharedKoinModule + viewModelsModule

            startKoin {
                androidContext(this@AppConfig)
                modules(module)
            }
        } catch (e: Exception) {
            Log.e(tag, "initKoin: ${e.message}")
        }
    }
}
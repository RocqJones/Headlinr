package com.zonkesoft.headlinr.data.vm

import com.zonkesoft.headlinr.BaseViewModel
import com.zonkesoft.headlinr.data.models.MenuItem
import com.zonkesoft.headlinr.data.models.SplashScreenModel
import com.zonkesoft.headlinr.data.models.Topics
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class InterfaceViewModel() : BaseViewModel() {

    private val _splashScreenModel = MutableStateFlow(SplashScreenModel(header = "", subHeader = "", delay = 0L))
    val splashScreenModel: StateFlow<SplashScreenModel> get() = _splashScreenModel

    private val _menuItems = MutableStateFlow(emptyList<MenuItem>())
    val menuItems: StateFlow<List<MenuItem>> get() = _menuItems

    private val _topics = MutableStateFlow(emptyList<Topics>())
    val topics: StateFlow<List<Topics>> get() = _topics

    private val _todayDate = MutableStateFlow<String?>(null)
    val todayDate: StateFlow<String?> get() = _todayDate

    init {
        setupSplashScreen()
        setupMenuItems()
        setupTopics()
        setupTodayDate()
    }

    private fun setupTodayDate() {
        try {
            val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            val dayOfWeek = now.date.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() } // e.g., "Monday"
            val day = now.date.dayOfMonth.toString().padStart(2, '0') // e.g., "20"
            val month = now.date.month.name.lowercase().replaceFirstChar { it.uppercase() } // e.g., "October"
            val year = now.date.year
            _todayDate.value = "$dayOfWeek, $day $month $year"
        } catch (e: Exception) {
            Error("Error setting up today's date: ${e.message}")
        }
    }

    private fun setupTopics() {
        try {
            _topics.value = listOf(
                Topics(icon = null, title = "Business"),
                Topics(icon = null, title = "Entertainment"),
                Topics(icon = null, title = "Health"),
                Topics(icon = null, title = "Science"),
                Topics(icon = null, title = "Sports"),
                Topics(icon = null, title = "Technology")
            )
        } catch (e: Exception) {
            Error("Error setting up topics: ${e.message}")
        }
    }

    private fun setupMenuItems() {
        try {
            _menuItems.value = listOf(
                MenuItem(icon = null, title = "Donate"),
                MenuItem(icon = null, title = "Bookmarks"),
                MenuItem(icon = null, title = "Settings"),
                MenuItem(icon = null, title = "About"),
                MenuItem(icon = null, title = "Rate Us"),
                MenuItem(icon = null, title = "Share")
            )
        } catch (e: Exception) {
            Error("Error setting up menu items: ${e.message}")
        }
    }

    private fun setupSplashScreen() {
        try {
            _splashScreenModel.value = SplashScreenModel(
                header = "Headlinr",
                subHeader = "Your world. Your headlines. Your way.",
                delay = 3000L
            )
        } catch (e: Exception) {
            Error("Error setting up splash screen: ${e.message}")
        }
    }
}
package com.zonkesoft.headlinr

import platform.Foundation.NSBundle
import platform.UIKit.UIDevice

class IOSPlatform() : Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val apiKey: String = NSBundle.mainBundle.objectForInfoDictionaryKey("NEWS_API_KEY") as? String ?: ""
}

actual fun getPlatform(): Platform = IOSPlatform()
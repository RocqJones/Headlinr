package com.zonkesoft.headlinr

import platform.Foundation.NSBundle
import platform.Foundation.NSDictionary
import platform.Foundation.dictionaryWithContentsOfFile
import platform.UIKit.UIDevice

class IOSPlatform() : Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion

        ?: throw IllegalStateException("Secrets.plist not found in configuration")

    val apiKey: String = dict?.objectForKey("NEWS_API_KEY") as? String
        ?: throw IllegalStateException("NEWS_API_KEY not found in Secrets.plist")
}

actual fun getPlatform(): Platform = IOSPlatform()
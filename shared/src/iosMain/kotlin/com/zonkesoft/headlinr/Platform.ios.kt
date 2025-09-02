package com.zonkesoft.headlinr

import platform.Foundation.NSBundle
import platform.Foundation.NSDictionary
import platform.Foundation.dictionaryWithContentsOfFile
import platform.UIKit.UIDevice

class IOSPlatform() : Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion

    private val dict: NSDictionary? = NSBundle.mainBundle.pathForResource("Secrets", "plist")?.let {
        NSDictionary.dictionaryWithContentsOfFile(it) as NSDictionary?
    }
        ?: throw IllegalStateException("NEWS_API_KEY not found in configuration")
}

actual fun getPlatform(): Platform = IOSPlatform()
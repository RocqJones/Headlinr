package com.zonkesoft.headlinr

import platform.Foundation.NSBundle
import platform.Foundation.NSDictionary
import platform.Foundation.dictionaryWithContentsOfFile
import platform.UIKit.UIDevice

class IOSPlatform() : Platform {
    override val name: String = (UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion)

    val dict = NSDictionary.dictionaryWithContentsOfFile(
        NSBundle.mainBundle.pathForResource("Secrets", "plist") ?: ""
    ) as NSDictionary?

    override val apiKey: String = dict?.objectForKey("NEWS_API_KEY") as? String ?: ""
}

actual fun getPlatform(): Platform = IOSPlatform()
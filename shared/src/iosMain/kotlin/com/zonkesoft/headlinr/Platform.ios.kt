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
    override val apiKey: String = dict?.objectForKey("NEWS_API_KEY") as? String
        ?: throw IllegalStateException("Missing NEWS_API_KEY in Secrets.plist or Secrets.plist not found. Please ensure the file exists and contains the key.")
}

actual fun getPlatform(): Platform = IOSPlatform()
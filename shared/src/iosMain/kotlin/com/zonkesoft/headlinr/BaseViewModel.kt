package com.zonkesoft.headlinr

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.cancel

actual open class BaseViewModel {
    /*
   - Since on iOS we don't have lifecycle aware VM we'll create asynchronous scope ourselves.
   - Dispatchers.IO will lift all the work from the main thread but will run on input/output thread.
   - Since this isn't lifecycle aware as android we'll create a function that manually cancel processes
     to free up allocated memory.
   */
    actual val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    fun clear() {
        scope.cancel()
    }
}
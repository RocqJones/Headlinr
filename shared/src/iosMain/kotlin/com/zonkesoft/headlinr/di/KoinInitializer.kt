package com.zonkesoft.headlinr.di

import com.zonkesoft.headlinr.data.vm.InterfaceViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {
    // iOS does not have any VM specific module like Android
    val module =  sharedKoinModule

    startKoin {
        modules(module)
    }
}

/**
 * We have to expose the ViewModel to iOS in a separate class because we don't have koin libraries for iOS
 * This will be consumed in the iOS App
 */
class InterfaceInjector : KoinComponent {
    val interfaceViewModel: InterfaceViewModel by inject()
}

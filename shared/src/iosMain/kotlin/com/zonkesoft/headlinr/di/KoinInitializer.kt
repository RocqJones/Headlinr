package com.zonkesoft.headlinr.di

import com.zonkesoft.headlinr.presentation.vm.InterfaceViewModel
import com.zonkesoft.headlinr.presentation.vm.NewsViewModel
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
 * We have to expose the ViewModels to iOS in a separate class because we don't have koin libraries for iOS
 * We'll create wrappers for the ViewModels to be used in SwiftUI.
 *
 * @see InterfaceViewModel
 * @see NewsViewModel
 */
class InterfaceInjector : KoinComponent {
    val interfaceViewModel: InterfaceViewModel by inject()
}

class NewsInjector : KoinComponent {
    val newsViewModel: NewsViewModel by inject()
}
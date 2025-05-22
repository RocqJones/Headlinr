package com.zonkesoft.headlinr.di

import com.zonkesoft.headlinr.data.vm.InterfaceViewModel
import org.koin.dsl.module

val sourcesModule = module {
    single<InterfaceViewModel> { InterfaceViewModel() }
}

val sharedKoinModule = listOf(
    sourcesModule
)
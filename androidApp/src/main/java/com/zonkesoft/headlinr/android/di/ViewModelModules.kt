package com.zonkesoft.headlinr.android.di

import com.zonkesoft.headlinr.presentation.vm.InterfaceViewModel
import com.zonkesoft.headlinr.presentation.vm.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Android specific dependency graph for ViewModels
 * This module provides the ViewModels used in the Android application.
 */
val viewModelsModule = module {
    viewModel { InterfaceViewModel() }
    viewModel { NewsViewModel(get()) }
}

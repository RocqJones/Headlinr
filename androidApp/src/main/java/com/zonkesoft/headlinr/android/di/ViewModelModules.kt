package com.zonkesoft.headlinr.android.di

import com.zonkesoft.headlinr.data.vm.InterfaceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { InterfaceViewModel() }
}

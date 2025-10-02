package com.zonkesoft.headlinr.di

import com.zonkesoft.headlinr.data.repository.NewsRepository
import com.zonkesoft.headlinr.network.service.NewsService
import com.zonkesoft.headlinr.presentation.vm.InterfaceViewModel
import com.zonkesoft.headlinr.presentation.vm.NewsViewModel
import com.zonkesoft.headlinr.presentation.vm.SearchViewModel
import com.zonkesoft.headlinr.utils.Logger
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val sourcesModule = module {
    // Provide following as a singleton
    single<InterfaceViewModel> { InterfaceViewModel() }
    single<NewsService> { NewsService(get()) }
    single<NewsRepository> { NewsRepository(get()) }
    single<NewsViewModel> { NewsViewModel(get()) }
    single<SearchViewModel> { SearchViewModel(get()) }
}

val networkModule = module {
    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }

            install(Logging) {
                level = LogLevel.INFO
                logger = object : io.ktor.client.plugins.logging.Logger {
                    override fun log(message: String) {
                        Logger.api("HTTP_CLIENT", message)
                    }
                }
            }
        }
    }
}

val sharedKoinModule = listOf(
    sourcesModule,
    networkModule
)
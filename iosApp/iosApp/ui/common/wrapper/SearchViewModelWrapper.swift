//
//  SearchViewModelWrapper.swift
//  iosApp
//
//  Created by JonesMbindyo on 02/10/2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

@MainActor
class SearchViewModelWrapper: ObservableObject {

    let searchViewModel: SearchViewModel
    private var injector: SearchInjector

    // Published ui items
    @Published var searchState: SearchUiState
    @Published var searchQuery: String
    @Published var recentSearches: [String]

    init() {
        self.injector = SearchInjector()
        self.searchViewModel = injector.searchViewModel

        // init current values
        self.searchState = searchViewModel.searchState.value
        self.searchQuery = searchViewModel.searchQuery.value
        self.recentSearches = searchViewModel.recentSearches.value
    }

    func startObserving() {
        // Observe search state
        Task {
            for await item in searchViewModel.searchState {
                self.searchState = item
            }
        }

        // Observe search query
        Task {
            for await item in searchViewModel.searchQuery {
                self.searchQuery = item
            }
        }

        // Observe recent searches
        Task {
            for await item in searchViewModel.recentSearches {
                self.recentSearches = item
            }
        }
    }

    func setSearchQuery(_ query: String) {
        searchViewModel.setSearchQuery(query: query)
    }

    func getResultsByQuery(_ query: String) {
        searchViewModel.getResultsByQuery(query: query)
    }

    func clearRecentSearches() {
        searchViewModel.clearRecentSearches()
    }

    deinit {
        // Clean up if needed
    }
}

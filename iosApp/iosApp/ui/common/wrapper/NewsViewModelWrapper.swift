//
//  NewsViewModelWrapper.swift
//  iosApp
//
//  Created by JonesMbindyo on 18/06/2025.
//  Copyright © 2025 orgName. All rights reserved.
//

@MainActor
class NewsViewModelWrapper: ObservableObject {
    
    let newsViewModel: NewsViewModel
    private var injector: NewsInjector
    
    // Published ui items
    @Published var topStoriesState: TopStoriesUiState
    @Published var articlesModel: ArticlesModel
    @Published var highlightsState: HighlightsUiState
    @Published var trendingState: TrendingUiState
    @Published var viewAllTitle: String
    @Published var viewAllItems: [ArticlesModel]

    init() {
        self.injector = NewsInjector()
        self.newsViewModel = injector.newsViewModel
        
        // init current values
        self.topStoriesState = newsViewModel.topHeadlinesState.value
        self.articlesModel = newsViewModel.articlesModel.value
        self.highlightsState = newsViewModel.highlightsState.value
        self.trendingState = newsViewModel.trendingState.value
        self.viewAllTitle = newsViewModel.viewAllTitle.value
        self.viewAllItems = newsViewModel.viewAllItems.value
    }
    
    func startObserving() {
        // Observe states
        Task {
            for await item in newsViewModel.topHeadlinesState {
                self.topStoriesState = item
            }
        }
        
        Task {
            for await item in newsViewModel.articlesModel {
                self.articlesModel = item
            }
        }
        
        Task {
            for await item in newsViewModel.highlightsState {
                self.highlightsState = item
            }
        }
        
        Task {
            for await item in newsViewModel.trendingState {
                self.trendingState = item
            }
        }

        Task {
            for await item in newsViewModel.viewAllTitle {
                self.viewAllTitle = item
            }
        }

        Task {
            for await item in newsViewModel.viewAllItems {
                self.viewAllItems = item
            }
        }
    }
}

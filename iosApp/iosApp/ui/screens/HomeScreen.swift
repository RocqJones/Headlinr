//
//  HomeScreen.swift
//  iosApp
//
//  Created by JonesMbindyo on 08/05/2025.
//  Copyright © 2025 orgName. All rights reserved.
//
//
import SwiftUI
import shared

struct HomeScreen: View {
    @ObservedObject private(set) var interfaceViewModelWrapper: InterfaceViewModelWrapper
    @ObservedObject private(set) var newsViewModelWrapper: NewsViewModelWrapper
    @StateObject private var searchViewModelWrapper = SearchViewModelWrapper()

    @State private var showMenuSheet = false
    @State private var showProfileSheet = false
    @State private var navigateToViewMore = false
    @State private var navigateToViewAll = false
    @State private var navigateToSearch = false

    var body: some View {
        NavigationStack {
            ScrollView {
                VStack(alignment: .leading, spacing: 16) {
                    Divider()

                    if let today = interfaceViewModelWrapper.todayDate {
                        Text(today)
                            .font(.subheadline)
                    }

                    // TOP STORIES SECTION
                    HeaderRow(title: "Top Stories", action: "")
                    topStoriesSection

                    // TRENDING SECTION
                    HeaderRow(
                        title: "Trending",
                        action: "See All",
                        icon: "flame.fill"
                    ) {
                        switch newsViewModelWrapper.trendingState {
                        case let content as TrendingUiState.TrendingContent:
                            newsViewModelWrapper.newsViewModel.setViewAllItems(
                                title: "Trending",
                                isTopics: false,
                                list: content.trendingResults
                            )
                            navigateToViewAll = true
                        default:
                            break
                        }
                    }

                    trendingSection

                    // HIGHLIGHTS SECTION
                    HeaderRow(
                        title: "Highlights",
                        action: "See All",
                        icon: "newspaper.fill"
                    ) {
                        switch newsViewModelWrapper.highlightsState {
                        case let content as HighlightsUiState.HighlightsContent:
                            newsViewModelWrapper.newsViewModel.setViewAllItems(
                                title: "Highlights",
                                isTopics: false,
                                list: content.highlightResults
                            )
                            navigateToViewAll = true
                        default:
                            break
                        }
                    }

                    highlightsSection

                }.padding().onAppear {
                    newsViewModelWrapper.startObserving()
                }
            }
            .navigationTitle("For You")
            .toolbar {
                ToolbarItem(placement: .navigationBarLeading) {
                    Button(action: { showMenuSheet = true }) {
                        Image(systemName: "line.horizontal.3")
                    }
                }
                ToolbarItemGroup(placement: .navigationBarTrailing) {
                    Button(action: { navigateToSearch = true }) {
                        Image(systemName: "magnifyingglass")
                    }
                    Button(action: { showProfileSheet = true }) {
                        Image(systemName: "person.circle")
                    }
                }
            }
            .sheet(isPresented: $showMenuSheet) {
                MenuContent(viewModel: interfaceViewModelWrapper) { topicTitle in
                    showMenuSheet = false
                    newsViewModelWrapper.newsViewModel.getTopicsByQuery(query: topicTitle)
                    newsViewModelWrapper.newsViewModel.setViewAllItems(
                        title: topicTitle,
                        isTopics: true,
                        list: []
                    )
                    navigateToViewAll = true
                }
            }
            .sheet(isPresented: $showProfileSheet) {
                ProfileContent(viewModel: interfaceViewModelWrapper)
            }
            .navigationDestination(isPresented: $navigateToViewMore) {
                ViewMoreScreen(newsViewModelWrapper: newsViewModelWrapper)
            }
            .navigationDestination(isPresented: $navigateToViewAll) {
                ViewAllScreen(newsViewModelWrapper: newsViewModelWrapper)
            }
            .navigationDestination(isPresented: $navigateToSearch) {
                SearchScreen(
                    searchViewModelWrapper: searchViewModelWrapper,
                    newsViewModelWrapper: newsViewModelWrapper
                )
            }
        }
    }

    @ViewBuilder
    var topStoriesSection: some View {
        switch newsViewModelWrapper.topStoriesState {
        case let loading as TopStoriesUiState.Loading:
            if loading.loading {
                ProgressView().frame(maxWidth: .infinity).padding()
            }

        case let error as TopStoriesUiState.Error:
            ErrorMessageView(title: error.title, message: error.message)

        case let content as TopStoriesUiState.TopContent:
            TopStoriesContentView(
                stories: content.topStories,
                textColor: .primary
            ) { selectedStory in
                newsViewModelWrapper.newsViewModel.setArticlesModel(articlesModel: selectedStory)
                navigateToViewMore = true
            }

        default:
            EmptyView()
        }
    }

    @ViewBuilder
    var trendingSection: some View {
        switch newsViewModelWrapper.trendingState {
        case let loading as TrendingUiState.Loading:
            if loading.loading {
                ProgressView().frame(maxWidth: .infinity).padding()
            }

        case let error as TrendingUiState.Error:
            ErrorMessageView(title: error.title, message: error.message)

        case let content as TrendingUiState.TrendingContent:
            TrendingContent(
                trending: Array(content.trendingResults.prefix(10)),
                textColor: .primary
            ) { selectedItem in
                newsViewModelWrapper.newsViewModel.setArticlesModel(articlesModel: selectedItem)
                navigateToViewMore = true
            }

        default:
            EmptyView()
        }
    }

    @ViewBuilder
    var highlightsSection: some View {
        switch newsViewModelWrapper.highlightsState {
        case let loading as HighlightsUiState.Loading:
            if loading.loading {
                ProgressView().frame(maxWidth: .infinity).padding()
            }

        case let error as HighlightsUiState.Error:
            ErrorMessageView(title: error.title, message: error.message)

        case let content as HighlightsUiState.HighlightsContent:
            HighlightsContent(
                highlights: Array(content.highlightResults.prefix(10)),
                textColor: .primary
            ) { selectedItem in
                newsViewModelWrapper.newsViewModel.setArticlesModel(articlesModel: selectedItem)
                navigateToViewMore = true
            }

        default:
            EmptyView()
        }
    }
}

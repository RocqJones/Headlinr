//
//  HomeScreen.swift
//  iosApp
//
//  Created by JonesMbindyo on 08/05/2025.
//  Copyright © 2025 orgName. All rights reserved.
//
import SwiftUI
import shared

import SwiftUI
import shared

struct HomeScreen: View {
    @ObservedObject var interfaceViewModelWrapper: InterfaceViewModelWrapper
    @ObservedObject var newsViewModelWrapper: NewsViewModelWrapper

    @State private var showMenuSheet = false
    @State private var showProfileSheet = false
    @State private var navigateToViewMore = false

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
                    HeaderRow(title: "Top Stories", action: "See All")
                    topStoriesSection

                    // TRENDING SECTION
                    HeaderRow(title: "Trending", action: "See All", icon: "flame.fill")

                    // HIGHLIGHTS SECTION
                    HeaderRow(title: "Highlights", action: "See All")
                }
                .padding()
            }
            .navigationTitle("For You")
            .toolbar {
                ToolbarItem(placement: .navigationBarLeading) {
                    Button(action: { showMenuSheet = true }) {
                        Image(systemName: "line.horizontal.3")
                    }
                }
                ToolbarItemGroup(placement: .navigationBarTrailing) {
                    Button(action: {}) {
                        Image(systemName: "magnifyingglass")
                    }
                    Button(action: { showProfileSheet = true }) {
                        Image(systemName: "person.circle")
                    }
                }
            }
            .sheet(isPresented: $showMenuSheet) {
                MenuContent(viewModel: interfaceViewModelWrapper)
            }
            .sheet(isPresented: $showProfileSheet) {
                ProfileContent(viewModel: interfaceViewModelWrapper)
            }
            .navigationDestination(isPresented: $navigateToViewMore) {
                ViewMoreScreen(newsViewModelWrapper: newsViewModelWrapper)
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
}


struct HomeScreen_Previews: PreviewProvider {

    class MockInterfaceViewModelWrapper: InterfaceViewModelWrapper {
        override init() {
            super.init()
            self.todayDate = "May 22, 2025"
        }
    }

    class MockNewsViewModelWrapper: NewsViewModelWrapper {
        override init() {
            super.init()
            self.topStoriesState = TopStoriesUiState.TopContent(
                topStories: [
                    ArticlesModel(
                        source: SourceModel(id: "1", name: "Mock Source 1"),
                        author: "Author 1",
                        title: "Top Story Headline 1",
                        description: "Description for story 1",
                        url: "https://example.com/story1",
                        urlToImage: "https://via.placeholder.com/300.png",
                        publishedAt: "2025-05-22T12:00:00Z",
                        content: "Full content of the article 1"
                    ),
                    ArticlesModel(
                        source: SourceModel(id: "2", name: "Mock Source 2"),
                        author: "Author 2",
                        title: "Top Story Headline 2",
                        description: "Description for story 2",
                        url: "https://example.com/story2",
                        urlToImage: "https://via.placeholder.com/300.png",
                        publishedAt: "2025-05-22T14:00:00Z",
                        content: "Full content of the article 2"
                    )
                ]
            )
        }
    }

    static var previews: some View {
        HomeScreen(
            interfaceViewModelWrapper: MockInterfaceViewModelWrapper(),
            newsViewModelWrapper: MockNewsViewModelWrapper()
        )
        .preferredColorScheme(.light)
    }
}

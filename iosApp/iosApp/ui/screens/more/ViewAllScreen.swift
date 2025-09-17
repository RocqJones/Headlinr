//
//  ViewAllScreen.swift
//  iosApp
//
//  Created by JonesMbindyo on 02/09/2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ViewAllScreen: View {
    @ObservedObject var newsViewModelWrapper: NewsViewModelWrapper
    @Environment(\.dismiss) private var dismiss
    @State private var navigateToViewMore = false

    var body: some View {
        NavigationStack {
            ScrollView {
                VStack(alignment: .leading, spacing: 16) {
                    // Header with back button and title
                    HStack {
                        Button(action: {
                            dismiss()
                        }) {
                            HStack(spacing: 8) {
                                Image(systemName: "chevron.left")
                                    .foregroundColor(.blue)
                                Text(newsViewModelWrapper.viewAllTitle)
                                    .font(.title2)
                                    .fontWeight(.medium)
                                    .foregroundColor(.blue)
                            }
                        }

                        Spacer()
                    }
                    .padding(.horizontal)

                    if newsViewModelWrapper.isTopics {
                        topicsContent
                    } else {
                        // Regular content using viewAllItems
                        TrendingContent(
                            trending: newsViewModelWrapper.viewAllItems,
                            textColor: .primary
                        ) { selectedItem in
                            newsViewModelWrapper.newsViewModel.setArticlesModel(articlesModel: selectedItem)
                            navigateToViewMore = true
                        }
                        .padding(.horizontal)
                    }
                }
            }
            .navigationBarHidden(true)
            .navigationDestination(isPresented: $navigateToViewMore) {
                ViewMoreScreen(newsViewModelWrapper: newsViewModelWrapper)
            }
        }
        .navigationBarHidden(true)
    }

    @ViewBuilder
    var topicsContent: some View {
        switch newsViewModelWrapper.topicsState {
        case let loading as TopicsUiState.Loading:
            if loading.loading {
                ProgressView().frame(maxWidth: .infinity).padding()
            }

        case let error as TopicsUiState.Error:
            ErrorMessageView(title: error.title, message: error.message)

        case let content as TopicsUiState.TopicsContent:
            TrendingContent(
                trending: content.topicsResults,
                textColor: .primary
            ) { selectedItem in
                newsViewModelWrapper.newsViewModel.setArticlesModel(
                    articlesModel: selectedItem
                )
                navigateToViewMore = true
            }
            .padding(.horizontal)

        default:
            EmptyView()
        }
    }
}

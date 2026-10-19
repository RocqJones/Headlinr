//
//  SearchScreen.swift
//  iosApp
//
//  Created by JonesMbindyo on 02/10/2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SearchScreen: View {
    @ObservedObject private var searchViewModelWrapper: SearchViewModelWrapper
    @ObservedObject private var newsViewModelWrapper: NewsViewModelWrapper
    @Environment(\.dismiss) private var dismiss
    @State private var navigateToViewMore = false
    @FocusState private var isSearchFieldFocused: Bool

    init(searchViewModelWrapper: SearchViewModelWrapper, newsViewModelWrapper: NewsViewModelWrapper) {
        self.searchViewModelWrapper = searchViewModelWrapper
        self.newsViewModelWrapper = newsViewModelWrapper
    }

    var body: some View {
        NavigationStack {
            VStack(spacing: 0) {
                // Search Bar
                HStack(spacing: 12) {
                    Button(action: {
                        dismiss()
                    }) {
                        Image(systemName: "chevron.left")
                            .foregroundColor(.primary)
                            .font(.title2)
                    }

                    HStack {
                        Image(systemName: "magnifyingglass")
                            .foregroundColor(.secondary)

                        TextField("Search news...", text: Binding(
                            get: { searchViewModelWrapper.searchQuery },
                            set: { searchViewModelWrapper.setSearchQuery($0) }
                        ))
                        .focused($isSearchFieldFocused)
                        .submitLabel(.search)
                        .onSubmit {
                            if !searchViewModelWrapper.searchQuery.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty {
                                searchViewModelWrapper.getResultsByQuery(searchViewModelWrapper.searchQuery)
                                isSearchFieldFocused = false
                            }
                        }

                        if !searchViewModelWrapper.searchQuery.isEmpty {
                            Button(action: {
                                searchViewModelWrapper.setSearchQuery("")
                            }) {
                                Image(systemName: "xmark.circle.fill")
                                    .foregroundColor(.secondary)
                            }
                        }
                    }
                    .padding(.horizontal, 12)
                    .padding(.vertical, 8)
                    .background(Color(.systemGray6))
                    .cornerRadius(10)
                }
                .padding(.horizontal, 16)
                .padding(.top, 8)

                Divider()
                    .padding(.top, 16)

                // Content
                ScrollView {
                    VStack(alignment: .leading, spacing: 16) {
                        if searchViewModelWrapper.searchQuery.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty && !searchViewModelWrapper.recentSearches.isEmpty {
                            // Recent Searches Section
                            recentSearchesSection
                        } else {
                            // Search Results Section
                            searchResultsSection
                        }
                    }
                    .padding(.horizontal, 16)
                    .padding(.top, 16)
                }
            }
        }
        .navigationBarHidden(true)
        .onAppear {
            searchViewModelWrapper.startObserving()
            isSearchFieldFocused = true
        }
        .navigationDestination(isPresented: $navigateToViewMore) {
            ViewMoreScreen(newsViewModelWrapper: newsViewModelWrapper)
        }
    }

    @ViewBuilder
    var recentSearchesSection: some View {
        VStack(alignment: .leading, spacing: 12) {
            Text("Recent Searches")
                .font(.headline)
                .fontWeight(.bold)
                .frame(maxWidth: .infinity, alignment: .leading)

            ForEach(searchViewModelWrapper.recentSearches, id: \.self) { recentSearch in
                HStack {
                    Image(systemName: "clock.arrow.circlepath")
                        .foregroundColor(.secondary)
                        .font(.caption)

                    Text(recentSearch)
                        .font(.body)
                        .frame(maxWidth: .infinity, alignment: .leading)

                    Spacer()
                }
                .padding(.vertical, 8)
                .onTapGesture {
                    searchViewModelWrapper.setSearchQuery(recentSearch)
                    searchViewModelWrapper.getResultsByQuery(recentSearch)
                    isSearchFieldFocused = false
                }
            }

            Divider()

            Button(action: {
                searchViewModelWrapper.clearRecentSearches()
            }) {
                Text("Clear Recent Searches")
                    .font(.body)
                    .foregroundColor(.red)
                    .frame(maxWidth: .infinity, alignment: .leading)
            }
            .padding(.vertical, 8)
        }
    }

    @ViewBuilder
    var searchResultsSection: some View {
        switch searchViewModelWrapper.searchState {
        case let loading as SearchUiState.Loading:
            if loading.loading {
                VStack {
                    ProgressView()
                        .scaleEffect(1.2)
                    Text("Searching...")
                        .font(.subheadline)
                        .foregroundColor(.secondary)
                        .padding(.top, 8)
                }
                .frame(maxWidth: .infinity)
                .padding(.top, 50)
            }

        case let error as SearchUiState.Error:
            ErrorMessageView(title: error.title, message: error.message)
                .padding(.top, 50)

        case let content as SearchUiState.SearchContent:
            VStack(alignment: .leading, spacing: 16) {
                Text("Found \(content.searchResults.count) results for \"\(searchViewModelWrapper.searchQuery)\"")
                    .font(.subheadline)
                    .foregroundColor(.secondary)
                    .frame(maxWidth: .infinity, alignment: .leading)

                LazyVStack(spacing: 16) {
                    ForEach(Array(content.searchResults.enumerated()), id: \.element.url) { index, article in
                        SearchResultCard(article: article) {
                            newsViewModelWrapper.newsViewModel.setArticlesModel(articlesModel: article)
                            navigateToViewMore = true
                        }

                        if index < content.searchResults.count - 1 {
                            Divider()
                        }
                    }
                }
            }

        default:
            EmptyView()
        }
    }
}

struct SearchResultCard: View {
    let article: ArticlesModel
    let onTap: () -> Void

    var body: some View {
        HStack(spacing: 12) {
            // Text content
            VStack(alignment: .leading, spacing: 4) {
                Text(article.publishedAt ?? "DD/MM/YYYY")
                    .font(.caption2)
                    .foregroundColor(.secondary)

                Text(article.title ?? "Title")
                    .font(.subheadline)
                    .fontWeight(.semibold)
                    .multilineTextAlignment(.leading)
                    .lineLimit(3)

                if let description = article.description_, !description.isEmpty {
                    Text(description)
                        .font(.caption)
                        .foregroundColor(.secondary)
                        .multilineTextAlignment(.leading)
                        .lineLimit(2)
                }

                Text("Source: \(article.source?.name ?? "Unknown")")
                    .font(.caption2)
                    .foregroundColor(.secondary)
            }
            .frame(maxWidth: .infinity, alignment: .leading)

            // Image
            AsyncImage(url: URL(string: article.urlToImage ?? "")) { image in
                image
                    .resizable()
                    .aspectRatio(contentMode: .fill)
            } placeholder: {
                Rectangle()
                    .fill(Color(.systemGray5))
                    .overlay(
                        Image(systemName: "photo")
                            .foregroundColor(.secondary)
                    )
            }
            .frame(width: 80, height: 80)
            .clipShape(RoundedRectangle(cornerRadius: 8))
        }
        .contentShape(Rectangle())
        .onTapGesture {
            onTap()
        }
    }
}

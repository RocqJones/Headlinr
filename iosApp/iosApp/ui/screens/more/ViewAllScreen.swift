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

    var body: some View {
        NavigationView {
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

                    // Content list using TrendingContentView for consistent styling
                    TrendingContent(
                        trending: newsViewModelWrapper.viewAllItems,
                        textColor: .primary
                    ) { selectedItem in
                        newsViewModelWrapper.newsViewModel.setArticlesModel(articlesModel: selectedItem)
                        // Navigation to ViewMoreScreen would be handled by parent
                    }
                    .padding(.horizontal)
                }
            }
            .navigationBarHidden(true)
        }
        .navigationBarHidden(true)
    }
}

//
//  ViewMoreScreen.swift
//  iosApp
//
//  Created by JonesMbindyo on 18/06/2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import WebKit
import shared

import SwiftUI

struct ViewMoreScreen: View {
    @ObservedObject var newsViewModelWrapper: NewsViewModelWrapper
    @State private var showWebSheet = false

    var body: some View {
        let article = newsViewModelWrapper.articlesModel

        GeometryReader { geometry in
            ScrollView {
                VStack(alignment: .leading, spacing: 0) {
                    Spacer().frame(height: 20)

                    // Title
                    Text(article.title ?? "Title")
                        .font(.system(size: 20, weight: .bold))
                        .frame(maxWidth: .infinity, alignment: .leading)
                        .padding(.horizontal, 16)

                    Spacer().frame(height: 16)

                    // Author & Date
                    HStack {
                        Text("By: \(article.author ?? "Source")")
                            .font(.system(size: 14, weight: .medium))
                        Spacer()
                        Text(article.publishedAt ?? "DD/MM/YYYY")
                            .font(.system(size: 14, weight: .medium))
                    }
                    .frame(maxWidth: .infinity)
                    .padding(.horizontal, 16)

                    Spacer().frame(height: 8)

                    // Image
                    if let urlString = article.urlToImage, let url = URL(string: urlString) {
                        AsyncImage(url: url) { image in
                            image
                                .resizable()
                                .aspectRatio(contentMode: .fill)
                                .frame(width: geometry.size.width - 32, height: 250)
                                .clipped()
                                .cornerRadius(8)
                        } placeholder: {
                            Rectangle()
                                .fill(Color.gray.opacity(0.3))
                                .frame(width: geometry.size.width - 32, height: 250)
                                .overlay(ProgressView())
                                .cornerRadius(8)
                        }
                        .padding(.horizontal, 16)
                    }

                    Spacer().frame(height: 16)

                    // Source
                    let s = article.source?.name ?? "Source"
                    Text("Source: \(s)")
                        .font(.system(size: 14))
                        .frame(maxWidth: .infinity, alignment: .leading)
                        .padding(.horizontal, 16)

                    Spacer().frame(height: 16)

                    // Description
                    Text(article.description_ ?? "")
                        .font(.system(size: 16, weight: .medium))
                        .frame(maxWidth: .infinity, alignment: .leading)
                        .padding(.horizontal, 16)

                    Spacer().frame(height: 16)

                    // Content
                    Text(article.content ?? "")
                        .font(.system(size: 14))
                        .frame(maxWidth: .infinity, alignment: .leading)
                        .padding(.horizontal, 16)

                    Spacer().frame(height: 16)

                    // Read more
                    Text("Read more at...")
                        .font(.system(size: 16, weight: .bold))
                        .foregroundColor(.blue)
                        .frame(maxWidth: .infinity, alignment: .leading)
                        .onTapGesture {
                            showWebSheet = true
                        }
                        .padding(.horizontal, 16)

                    Spacer().frame(height: 16)
                }
                .frame(width: geometry.size.width)
            }
        }
        .navigationBarTitleDisplayMode(.inline)
        .sheet(isPresented: $showWebSheet) {
            if let url = article.url {
                WebViewBottomSheet(url: url)
                    .presentationDetents([.medium, .large])
            }
        }
    }
}

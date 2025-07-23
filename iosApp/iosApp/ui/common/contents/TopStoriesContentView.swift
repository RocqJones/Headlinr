//
//  TopStoriesContentView.swift
//  iosApp
//
//  Created by JonesMbindyo on 18/06/2025.
//  Copyright © 2025 orgName. All rights reserved.
//
import SwiftUI
import shared

struct TopStoriesContentView: View {
    let stories: [ArticlesModel]
    let textColor: Color
    let onStoryTap: (ArticlesModel) -> Void

    @State private var currentIndex: Int = 0

    var body: some View {
        VStack(alignment: .leading) {
            ScrollView(.horizontal, showsIndicators: false) {
                HStack(spacing: 12) {
                    ForEach(stories.indices, id: \.self) { index in
                        GeometryReader { geo in
                            VStack(alignment: .leading) {
                                AsyncImage(url: URL(string: stories[index].urlToImage ?? "")) { phase in
                                    if let image = phase.image {
                                        image
                                            .resizable()
                                            .scaledToFill()
                                            .frame(width: 300, height: 200)
                                            .clipped()
                                            .cornerRadius(10)
                                    } else if phase.error != nil {
                                        Color.gray
                                            .frame(width: 300, height: 200)
                                            .overlay(Text("Image Error").foregroundColor(.white))
                                    } else {
                                        ProgressView()
                                            .frame(width: 300, height: 200)
                                    }
                                }

                                Text(stories[index].source?.name ?? "Source")
                                    .font(.caption)
                                    .foregroundColor(textColor)

                                Text(stories[index].title ?? "Title")
                                    .font(.headline)
                                    .foregroundColor(textColor)
                                    .lineLimit(2)

                                Text(stories[index].publishedAt ?? "Date")
                                    .font(.caption2)
                                    .foregroundColor(textColor)
                            }
                            .frame(width: 300)
                            .onTapGesture {
                                onStoryTap(stories[index])
                            }
                            .onChange(of: geo.frame(in: .global).minX) { _ in
                                let midX = geo.frame(in: .global).midX
                                let screenMid = UIScreen.main.bounds.width / 2
                                let offset = abs(screenMid - midX)
                                if offset < 150 { // Threshold
                                    currentIndex = index
                                }
                            }
                        }
                        .frame(width: 300, height: 280)
                    }
                }
                .padding(.horizontal)
            }

            // Page Indicators
            HStack {
                ForEach(stories.indices, id: \.self) { index in
                    Circle()
                        .fill(index == currentIndex ? textColor : .gray.opacity(0.3))
                        .frame(width: index == currentIndex ? 12 : 8, height: index == currentIndex ? 12 : 8)
                }
            }
            .frame(maxWidth: .infinity)
            .padding(.top, 12)
        }
    }
}

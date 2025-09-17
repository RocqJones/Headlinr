//
//  TrendingContent.swift
//  iosApp
//
//  Created by JonesMbindyo on 02/09/2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

struct TrendingContent: View {
    let trending: [ArticlesModel]
    let textColor: Color
    let onItemTap: (ArticlesModel) -> Void

    var body: some View {
        VStack(spacing: 0) {
            ForEach(Array(trending.enumerated()), id: \.element.url) { index, item in
                HStack(spacing: 12) {
                    // Text content - 80% width
                    VStack(alignment: .leading, spacing: 4) {
                        Text(item.publishedAt ?? "DD/MM/YYYY")
                            .font(.caption2)
                            .foregroundColor(textColor.opacity(0.7))

                        Text(item.title ?? "Title")
                            .font(.subheadline)
                            .fontWeight(.bold)
                            .foregroundColor(textColor)
                            .multilineTextAlignment(.leading)

                        Text(item.description_ ?? "Description")
                            .font(.caption)
                            .foregroundColor(textColor.opacity(0.8))
                            .multilineTextAlignment(.leading)
                            .lineLimit(2)

                        Text("Source: \(item.source?.name ?? "Source")")
                            .font(.caption2)
                            .foregroundColor(textColor.opacity(0.7))
                    }
                    .frame(maxWidth: .infinity, alignment: .leading)

                    // Image - 20% width
                    AsyncImage(url: URL(string: item.urlToImage ?? "")) { image in
                        image
                            .resizable()
                            .aspectRatio(contentMode: .fill)
                    } placeholder: {
                        Rectangle()
                            .fill(Color.gray.opacity(0.3))
                    }
                    .frame(width: 100, height: 80)
                    .clipShape(RoundedRectangle(cornerRadius: 8))
                }
                .onTapGesture {
                    onItemTap(item)
                }

                if index != trending.count - 1 {
                    Divider()
                        .padding(.vertical, 8)
                }
            }
        }
    }
}

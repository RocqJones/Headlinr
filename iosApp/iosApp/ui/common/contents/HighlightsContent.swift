//
//  HighlightsContent.swift
//  iosApp
//
//  Created by JonesMbindyo on 02/09/2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

struct HighlightsContent: View {
    let highlights: [ArticlesModel]
    let textColor: Color
    let onItemTap: (ArticlesModel) -> Void

    var body: some View {
        VStack(spacing: 0) {
            ForEach(Array(highlights.enumerated()), id: \.element.url) { index, item in
                HStack(spacing: 12) {
                    // Text content - 80% width
                    VStack(alignment: .leading, spacing: 4) {
                        Text(item.title ?? "Title")
                            .font(.body)
                            .fontWeight(.bold)
                            .foregroundColor(textColor)
                            .multilineTextAlignment(.leading)

                        Text(item.description_ ?? "Description")
                            .font(.subheadline)
                            .foregroundColor(textColor.opacity(0.8))
                            .multilineTextAlignment(.leading)
                            .lineLimit(3)

                        Spacer(minLength: 4)

                        Text("By: \(item.author ?? "Author")")
                            .font(.caption)
                            .foregroundColor(textColor.opacity(0.7))

                        Text(item.publishedAt ?? "DD/MM/YYYY")
                            .font(.caption)
                            .foregroundColor(textColor.opacity(0.7))

                        Text("Source: \(item.source?.name ?? "Source")")
                            .font(.caption)
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

                if index != highlights.count - 1 {
                    Divider()
                        .padding(.vertical, 8)
                }
            }
        }
    }
}

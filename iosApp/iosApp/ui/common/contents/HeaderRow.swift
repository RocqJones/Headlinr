//
//  HeaderRow.swift
//  iosApp
//
//  Created by JonesMbindyo on 22/05/2025.
//  Copyright © 2025 orgName. All rights reserved.
//
import SwiftUI

struct HeaderRow: View {
    let title: String
    let action: String
    var icon: String? = nil
    var onActionTap: (() -> Void)? = nil

    var body: some View {
        HStack {
            HStack(spacing: 8) {
                if let icon = icon {
                    Image(systemName: icon)
                        .foregroundColor(.orange)
                }
                Text(title)
                    .font(.title2)
                    .fontWeight(.bold)
            }

            Spacer()

            if !action.isEmpty {
                Button(action: {
                    onActionTap?()
                }) {
                    Text(action)
                        .font(.subheadline)
                        .foregroundColor(.blue)
                }
            }
        }
    }
}

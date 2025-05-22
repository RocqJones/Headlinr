//
//  HeaderRow.swift
//  iosApp
//
//  Created by JonesMbindyo on 22/05/2025.
//  Copyright © 2025 orgName. All rights reserved.
//
import SwiftUI

struct HeaderRow: View {
    var title: String
    var action: String
    var icon: String? = nil

    var body: some View {
        HStack {
            if let icon = icon {
                Label(title, systemImage: icon)
                    .font(.headline)
                    .labelStyle(.titleOnly)
            } else {
                Text(title)
                    .font(.headline)
            }

            Spacer()
            Text(action)
                .foregroundColor(.blue)
                .font(.subheadline)
        }
    }
}


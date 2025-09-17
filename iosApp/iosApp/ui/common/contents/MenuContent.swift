//
//  MenuContent.swift
//  iosApp
//
//  Created by JonesMbindyo on 22/05/2025.
//  Copyright © 2025 orgName. All rights reserved.
//
import SwiftUI
import shared

struct MenuContent: View {
    @ObservedObject var viewModel: InterfaceViewModelWrapper
    var onTopicClick: (String) -> Void = { _ in }

    var body: some View {
        NavigationView {
            List {
                Section(header: Text("Topics")) {
                    ForEach(Array(viewModel.topics.enumerated()), id: \.offset) { index, topic in
                        HStack {
                            Text(topic.title)
                            Spacer()
                            Image(systemName: "arrow.up.right")
                                .foregroundColor(Color.logoColor)
                        }
                        .contentShape(Rectangle())
                        .onTapGesture {
                            onTopicClick(topic.title)
                        }
                    }
                }
            }
            .navigationTitle("Explore")
        }
    }
}

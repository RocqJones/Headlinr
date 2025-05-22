//
//  ProfileContent.swift
//  iosApp
//
//  Created by JonesMbindyo on 22/05/2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ProfileContent: View {
    @ObservedObject var viewModel: InterfaceViewModelWrapper

    var body: some View {
        NavigationView {
            List {
                Section(header: Text("Quick Actions")) {
                    ForEach(Array(viewModel.menuItems.enumerated()), id: \.offset) { index, item in
                        VStack(alignment: .leading) {
                            Text(item.title)
                                .font(.body)
                        }
                    }
                }
            }
            .navigationTitle("Profile")
        }
    }
}

//
//  AppEntry.swift
//  iosApp
//
//  Created by JonesMbindyo on 08/07/2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AppEntry: View {
    @State private var showHome = false
    private let interfaceViewModelWrapper = InterfaceViewModelWrapper()
    private let newsViewModelWrapper = NewsViewModelWrapper()

    var body: some View {
        if showHome {
            HomeScreen(
                interfaceViewModelWrapper: interfaceViewModelWrapper,
                newsViewModelWrapper: newsViewModelWrapper
            )
        } else {
            SplashScreen(viewModelWrapper: interfaceViewModelWrapper) {
                withAnimation {
                    showHome = true
                }
            }
        }
    }
}

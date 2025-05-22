//
//  SplashScreen.swift
//  iosApp
//
//  Created by JonesMbindyo on 08/05/2025.
//  Copyright © 2025 orgName. All rights reserved.
//
import SwiftUI
import shared

struct SplashScreen: View {
    @State private var navigateToHome = false
    @ObservedObject private(set) var viewModelWrapper: InterfaceViewModelWrapper
    
    var body: some View {
        NavigationStack {
            ZStack {
                Color(.systemBackground).ignoresSafeArea()
                
                VStack {
                    Spacer()
                    
                    Image("logo_transparent")
                        .resizable()
                        .frame(width: 250, height: 250)
                    
                    Spacer()
                    
                    VStack(alignment: .leading, spacing: 8) {
                        Text(viewModelWrapper.splashScreenModel.header)
                            .font(.system(size: 28, weight: .bold))
                            .foregroundColor(Color(.label))
                            .frame(maxWidth: .infinity, alignment: .leading)
                        
                        Text(viewModelWrapper.splashScreenModel.subHeader)
                            .font(.system(size: 16, weight: .medium))
                            .foregroundColor(Color(.label))
                            .frame(maxWidth: .infinity, alignment: .leading)
                    }
                    .padding(.bottom, 32)
                    .padding(.horizontal, 16)
                }
            }
            .onAppear {
                DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(Int(viewModelWrapper.splashScreenModel.delay))) {
                    navigateToHome = true
                }
            }.navigationDestination(isPresented: $navigateToHome) {
                HomeScreen(viewModelWrapper: InterfaceViewModelWrapper())
            }
        }
    }
}


struct SplashScreen_Previews: PreviewProvider {
    class MockViewModelWrapper: InterfaceViewModelWrapper {
        init(mockModel: SplashScreenModel) {
            super.init()
            self.splashScreenModel = mockModel
        }

        // Override to avoid starting actual KMP observation during preview
        override func startObserving() {
            // Do nothing in preview
        }
    }
    
    static var previews: some View {
        let mockModel = SplashScreenModel(
            header: "Welcome",
            subHeader: "Let's get started",
            delay: 2000
        )
        let mockWrapper = MockViewModelWrapper(mockModel: mockModel)
        
        return SplashScreen(viewModelWrapper: mockWrapper)
    }
}

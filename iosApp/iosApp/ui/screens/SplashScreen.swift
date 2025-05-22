//
//  SplashScreen.swift
//  iosApp
//
//  Created by JonesMbindyo on 08/05/2025.
//  Copyright © 2025 orgName. All rights reserved.
//
import SwiftUI
import shared

extension SplashScreen {
    
    @MainActor
    class InterfaceViewModelWrapper: ObservableObject {
       
        let interfaceViewModel: InterfaceViewModel

        private var injector: InterfaceInjector

        init() {
            injector = InterfaceInjector()
            interfaceViewModel = injector.interfaceViewModel
            splashScreenModel = interfaceViewModel.splashScreenModel.value
        }
        
        @Published var splashScreenModel : SplashScreenModel
        
        func startObserving() {
            Task {
                for await item in interfaceViewModel.splashScreenModel {
                    self.splashScreenModel = item
                }
            }
        }
    }
}

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
                HomeScreen()
            }
        }
    }
}

//struct SplashScreen_Previews : PreviewProvider {
//    static var previews: some View {
//        SplashScreen()
//    }
//}
struct SplashScreen_Previews: PreviewProvider {
    class MockViewModelWrapper: SplashScreen.InterfaceViewModelWrapper {
        init(mockModel: SplashScreenModel) {
            super.init()
            self.splashScreenModel = mockModel
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

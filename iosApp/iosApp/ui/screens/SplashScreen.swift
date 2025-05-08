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
                        Text("Headlinr")
                            .font(.system(size: 28, weight: .bold))
                            .foregroundColor(Color(.label))
                            .frame(maxWidth: .infinity, alignment: .leading)
                        
                        Text("Your world. Your headlines. Your way.")
                            .font(.system(size: 16, weight: .medium))
                            .foregroundColor(Color(.label))
                            .frame(maxWidth: .infinity, alignment: .leading)
                    }
                    .padding(.bottom, 32)
                    .padding(.horizontal, 16)
                }
            }
            .onAppear {
                DispatchQueue.main.asyncAfter(deadline: .now() + 3) {
                    navigateToHome = true
                }
            }.navigationDestination(isPresented: $navigateToHome) {
                HomeScreen()
            }
        }
    }
}

struct SplashScreen_Previews : PreviewProvider {
    static var previews: some View {
        SplashScreen()
    }
}

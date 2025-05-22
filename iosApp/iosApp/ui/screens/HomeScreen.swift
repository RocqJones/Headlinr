//
//  HomeScreen.swift
//  iosApp
//
//  Created by JonesMbindyo on 08/05/2025.
//  Copyright © 2025 orgName. All rights reserved.
//
import SwiftUI
import shared

struct HomeScreen: View {
    @ObservedObject var viewModelWrapper: InterfaceViewModelWrapper
    @State private var showMenuSheet = false
    @State private var showProfileSheet = false

    var body: some View {
        NavigationStack {
            ScrollView {
                VStack(alignment: .leading, spacing: 16) {
                    Divider()
                    
                    if let today = viewModelWrapper.todayDate {
                        Text(today)
                            .font(.subheadline)
                    }

                    HeaderRow(title: "Top Stories", action: "See All")
                    HeaderRow(title: "Trending", action: "See All", icon: "flame.fill")
                    HeaderRow(title: "Highlights", action: "See All")
                }
                .padding()
            }
            .navigationTitle("For You")
            .toolbar {
                ToolbarItem(placement: .navigationBarLeading) {
                    Button(action: { showMenuSheet = true }) {
                        Image(systemName: "line.horizontal.3")
                    }
                }
                ToolbarItemGroup(placement: .navigationBarTrailing) {
                    Button(action: {}) {
                        Image(systemName: "magnifyingglass")
                    }
                    Button(action: { showProfileSheet = true }) {
                        Image(systemName: "person.circle")
                    }
                }
            }
            .sheet(isPresented: $showMenuSheet) {
                MenuContent(viewModel: viewModelWrapper)
            }
            .sheet(isPresented: $showProfileSheet) {
                ProfileContent(viewModel: viewModelWrapper)
            }
        }
    }
}


struct HomeScreen_Previews: PreviewProvider {
    class MockViewModelWrapper: InterfaceViewModelWrapper {
        override init() {
            super.init()
            self.todayDate = "May 22, 2025"
        }
    }

    static var previews: some View {
        HomeScreen(viewModelWrapper: MockViewModelWrapper())
            .preferredColorScheme(.dark) // or .dark for dark mode preview
    }
}

//
//  InterfaceViewModelWrapper.swift
//  iosApp
//
//  Created by JonesMbindyo on 22/05/2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import shared

import Foundation
import shared

@MainActor
class InterfaceViewModelWrapper: ObservableObject {
    
    let interfaceViewModel: InterfaceViewModel
    private var injector: InterfaceInjector

    // Published ui items
    @Published var splashScreenModel: SplashScreenModel
    @Published var menuItems: [MenuItem]
    @Published var topics: [Topics]
    @Published var todayDate: String?

    init() {
        self.injector = InterfaceInjector()
        self.interfaceViewModel = injector.interfaceViewModel

        // Initialize with current values
        self.splashScreenModel = interfaceViewModel.splashScreenModel.value
        self.menuItems = interfaceViewModel.menuItems.value
        self.topics = interfaceViewModel.topics.value
        self.todayDate = interfaceViewModel.todayDate.value
    }

    func startObserving() {
        Task {
            // Observe SplashScreenModel
            for await item in interfaceViewModel.splashScreenModel {
                self.splashScreenModel = item
            }
        }

        Task {
            // Observe Menu Items
            for await items in interfaceViewModel.menuItems {
                self.menuItems = items
            }
        }

        Task {
            // Observe Topics
            for await topics in interfaceViewModel.topics {
                self.topics = topics
            }
        }

        Task {
            // Observe Today Date
            for await date in interfaceViewModel.todayDate {
                self.todayDate = date
            }
        }
    }
}

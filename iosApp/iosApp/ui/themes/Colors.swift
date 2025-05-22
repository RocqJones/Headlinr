//
//  Colors.swift
//  iosApp
//
//  Created by JonesMbindyo on 22/05/2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

extension Color {
    static let logoColor = Color(hex: "#1DB871")
    
    init(hex: String) {
        let scanner = Scanner(string: hex)
        _ = scanner.scanString("#")
            
        var rgb: UInt64 = 0
        scanner.scanHexInt64(&rgb)
        
        let r = Double((rgb >> 16) & 0xFF) / 255
        let g = Double((rgb >> 8) & 0xFF) / 255
        let b = Double(rgb & 0xFF) / 255
        
        self.init(red: r, green: g, blue: b)
    }
}

//
//  WebViewBottomSheet.swift
//  iosApp
//
//  Created by JonesMbindyo on 09/07/2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import WebKit

struct WebViewBottomSheet: View {
    let url: String
    @State private var isLoading = true

    var body: some View {
        ZStack(alignment: .top) {
            WebView(url: URL(string: url)!, isLoading: $isLoading)
                .edgesIgnoringSafeArea(.bottom)

            if isLoading {
                ProgressView()
                    .progressViewStyle(.linear)
                    .padding()
            }
        }
    }
}

struct WebView: UIViewRepresentable {
    let url: URL
    @Binding var isLoading: Bool

    func makeUIView(context: Context) -> WKWebView {
        let webView = WKWebView()
        webView.navigationDelegate = context.coordinator
        webView.scrollView.isScrollEnabled = true
        webView.load(URLRequest(url: url))
        return webView
    }

    func updateUIView(_ webView: WKWebView, context: Context) {
        // Optionally update view
    }

    func makeCoordinator() -> Coordinator {
        Coordinator(isLoading: $isLoading)
    }

    class Coordinator: NSObject, WKNavigationDelegate {
        @Binding var isLoading: Bool

        init(isLoading: Binding<Bool>) {
            _isLoading = isLoading
        }

        func webView(_ webView: WKWebView, didFinish navigation: WKNavigation!) {
            isLoading = false
        }
    }
}

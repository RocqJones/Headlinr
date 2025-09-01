package com.zonkesoft.headlinr.android.ui.common.contents

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import com.zonkesoft.headlinr.android.ui.common.dialogs.LottieLoader

@SuppressLint("SetJavaScriptEnabled", "ClickableViewAccessibility")
@Composable
fun WebViewBottomSheet(
    url: String,
    textColor: Color,
    backgroundColor: Color
) {
    var isLoading by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.80f)
            .background(backgroundColor)
    ) {
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    // Set explicit layout parameters
                    this.layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )

                    settings.apply {
                        javaScriptEnabled = true
                        domStorageEnabled = true
                        loadWithOverviewMode = true
                        useWideViewPort = true
                        builtInZoomControls = true
                        displayZoomControls = false
                        setSupportZoom(true)

                        // Additional settings for better touch handling
                        allowFileAccess = true
                        allowContentAccess = true
                        setGeolocationEnabled(false)
                    }

                    // Enable scrolling and ensure touch events are handled
                    isVerticalScrollBarEnabled = true
                    isHorizontalScrollBarEnabled = true
                    isScrollContainer = true

                    // Override touch handling to ensure WebView receives events
                    setOnTouchListener { view, event ->
                        when (event.action) {
                            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                                // Request that parent doesn't intercept touch events
                                view.parent?.requestDisallowInterceptTouchEvent(true)
                            }
                            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                                view.parent?.requestDisallowInterceptTouchEvent(false)
                            }
                        }
                        // Let WebView handle the touch event
                        false
                    }

                    // Set clients
                    webViewClient = object : WebViewClient() {
                        override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                            isLoading = true
                        }

                        override fun onPageFinished(view: WebView?, url: String?) {
                            isLoading = false
                        }

                        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                            return false // Let WebView handle URL loading
                        }
                    }
                }
            },
            update = { webView ->
                if (webView.url != url) {
                    webView.loadUrl(url)
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize().background(backgroundColor),
                contentAlignment = Alignment.TopCenter
            ) {
                LottieLoader(textColor = textColor)
            }
        }
    }
}

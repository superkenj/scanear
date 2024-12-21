package com.example.scanearmob

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val myWebView: WebView = findViewById(R.id.my_web_view)

        // Enable debugging for WebView
        WebView.setWebContentsDebuggingEnabled(true)

        // Enable JavaScript and localStorage
        myWebView.settings.javaScriptEnabled = true
        myWebView.settings.domStorageEnabled = true
        myWebView.settings.allowFileAccess = true

        // Set WebChromeClient for advanced features
        myWebView.webChromeClient = WebChromeClient()

        // Configure WebViewClient to handle local content securely
        myWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false // Ensure URLs are handled within the WebView
            }

            override fun onReceivedError(
                view: WebView?,
                errorCode: Int,
                description: String?,
                failingUrl: String?
            ) {
                super.onReceivedError(view, errorCode, description, failingUrl)
                android.util.Log.e("WebViewError", "Error: $description, URL: $failingUrl")
            }
        }

        // Load a local or remote URL
        myWebView.loadUrl("file:///android_asset/www/index.html")

    }
}

package com.example.hackathonwinnersapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.hackatonwinnersapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setWebview()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebview() {
        val webView: WebView = findViewById(R.id.webview)
        val address = "http://ya.ru"

        with(webView) {
            webViewClient = WebViewClient()
            loadUrl(address)
            settings.javaScriptEnabled = true
        }
    }
}
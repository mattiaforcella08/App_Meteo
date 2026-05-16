package com.example.cloudguard

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class Mappa : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mappa)

        val webView = findViewById<WebView>(R.id.mapWebView)

        // IMPORTANTE: Abilita JavaScript
        webView.settings.javaScriptEnabled = true

        // Evita che i link si aprano nel browser esterno
        webView.webViewClient = WebViewClient()

        // Carica la pagina HTML locale dagli assets
        webView.loadUrl("file:///android_asset/mappa.html")
    }
}
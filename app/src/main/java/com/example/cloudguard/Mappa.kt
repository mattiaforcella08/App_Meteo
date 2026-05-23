package com.example.cloudguard

import android.content.Intent
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Mappa : AppCompatActivity() {
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mappa)

        webView = findViewById(R.id.mapWebView)

        // Configurazione WebView e attivazione JavaScript
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        // Creazione del ponte tra Kotlin e il file HTML (window.Android)
        webView.addJavascriptInterface(WebAppInterface(), "Android")

        // Caricamento della mappa dagli assets
        webView.loadUrl("file:///android_asset/mappa.html")
    }

    // Classe interna che riceve i dati inviati dal JavaScript di Leaflet
    inner class WebAppInterface {
        @JavascriptInterface
        fun onMarkerClick(dest: String,) {
            try {
                // Settiamo la destinazione
                val homepage = Class.forName("com.example.cloudguard.Homepage")


                // Configurazione dell'Intent per aprire la nuova Activity
                val intent = Intent(this@Mappa, homepage)  //@Mappa serve pk se no kotlin nn sa se this si riferisce a activity o a webInterface

                intent.putExtra("Destinazione",dest);

                // Avvio della nuova Activity
                startActivity(intent)
            } catch (e:ClassNotFoundException) {
                // Questo errore esce se hai scritto male il nome nel JS o non hai ancora creato l'Activity in Android Studio
                Toast.makeText(this@Mappa, "Activity non trovata: $dest", Toast.LENGTH_LONG).show()
                e.printStackTrace();
            }
        }
    }
}
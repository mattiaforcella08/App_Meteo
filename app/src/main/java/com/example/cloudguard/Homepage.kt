package com.example.cloudguard

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Homepage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        val spinner: Spinner = findViewById(R.id.menu_tendina)
        val icona: TextView = findViewById(R.id.meteo_icona)
        val temperatura: TextView = findViewById(R.id.temperatura)
        val descrizione: TextView = findViewById(R.id.meteo_scritto)
        val btnAggiorna: Button = findViewById(R.id.btn_aggiorna)

        val opzioniCitta = arrayOf("Roma", "Milano", "Napoli")

        // Colleghiamo le opzioni allo Spinner tramite un Adapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opzioniCitta)
        spinner.adapter = adapter

        // Mappa dei dati
        val datiMeteo = mapOf(
            "Roma" to Triple("☀️", "25°C", "Sereno"),
            "Milano" to Triple("☁️", "18°C", "Nuvoloso"),
            "Napoli" to Triple("🌤️", "22°C", "Poco nuvoloso")
        )

        btnAggiorna.setOnClickListener {
            val cittaSelezionata = spinner.selectedItem.toString()

            val info = datiMeteo[cittaSelezionata]

            if (info != null) {
                icona.text = info.first
                temperatura.text = info.second
                descrizione.text = info.third
            }
        }
    }
}
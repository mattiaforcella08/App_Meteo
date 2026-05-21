package com.example.cloudguard

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Homepage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        val spinner: Spinner = findViewById(R.id.menu_tendina)
        val temperatura: TextView = findViewById(R.id.temperatura)
        val descrizione: TextView = findViewById(R.id.meteo_scritto)
        val btnAggiorna: Button = findViewById(R.id.btn_aggiorna)

        val leggiCitta = leggiJson()
        val nomiCitta = leggiCitta.map{ it.nome }


        // Colleghiamo le opzioni allo Spinner tramite un Adapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, nomiCitta)
        spinner.adapter = adapter

        btnAggiorna.setOnClickListener {
            val cittaSelezionata = spinner.selectedItem.toString()
            val info = leggiCitta.find {
                it.nome == cittaSelezionata
            }
            if (info != null) {
                temperatura.text = "Temperatura: ${info.temperatura}°"
                descrizione.text = info.descrizione
            }
        }
    }
    private fun leggiJson(): List<Citta> {

        val json = assets
            .open("citta.json")
            .bufferedReader()
            .use { it.readText() }

        val type = object : TypeToken<List<Citta>>() {}.type

        return Gson().fromJson(json, type)
    }
}
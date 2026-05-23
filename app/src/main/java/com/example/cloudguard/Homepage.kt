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

        val nome: TextView = findViewById(R.id.nome)
        val temperatura: TextView = findViewById(R.id.temperatura)
        val descrizione: TextView = findViewById(R.id.descrizione)

        val cittaInput  = intent.getStringExtra("Destinazione")
        val listaCitta = leggiJson()

        val info = listaCitta.find {
            it.nome == cittaInput
        }
        if (info != null) {
            nome.text = info.nome
            temperatura.text = "${info.temperatura}°"
            descrizione.text = info.descrizione
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
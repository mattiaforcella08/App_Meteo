package com.example.cloudguard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Avvio : AppCompatActivity() {

    lateinit var Avvio: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_avvio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Avvio = findViewById<Button>(R.id.BottoneAvvio);

        Avvio.setOnClickListener {

            // Intent = passaggio da un activity all'altra
            val intent = Intent(this, Homepage::class.java);
            startActivity(intent);

        }

    }
}
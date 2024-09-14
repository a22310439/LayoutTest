package com.example.layouttest

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var countMeGusta = 0
    private var countNoMeGusta = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnMeGusta = findViewById<Button>(R.id.btnMeGusta)
        val btnNoMeGusta = findViewById<Button>(R.id.btnNoMeGusta)
        val textMeGusta = findViewById<TextView>(R.id.contMeGusta)
        val textNoMeGusta = findViewById<TextView>(R.id.contNoMeGusta)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Restaura los contadores si ya se había guardado el estado
        savedInstanceState?.let {
            countMeGusta = it.getInt("countMeGusta", 0)
            countNoMeGusta = it.getInt("countNoMeGusta", 0)
            textMeGusta.text = countMeGusta.toString()
            textNoMeGusta.text = countNoMeGusta.toString()
        }
        
        btnMeGusta.setOnClickListener {
            countMeGusta++
            textMeGusta.text = countMeGusta.toString()
        }

        btnNoMeGusta.setOnClickListener {
            countNoMeGusta++
            textNoMeGusta.text = countNoMeGusta.toString()
        }
    }

    // Guarda los contadores antes de la destrucción de la actividad
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("countMeGusta", countMeGusta)
        outState.putInt("countNoMeGusta", countNoMeGusta)
    }
}
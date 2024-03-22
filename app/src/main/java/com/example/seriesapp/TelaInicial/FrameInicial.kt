package com.example.seriesapp.TelaInicial

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.seriesapp.MainActivity
import com.example.seriesapp.R

// Classe responsável por exibir a tela inicial da aplicação
class FrameInicial : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.frameinicial)

        // Configurar o botão na tela inicial para iniciar a MainActivity
        val frameButton: ImageView = findViewById(R.id.FrameButton)
        frameButton.setOnClickListener {
            val intent = Intent(this@FrameInicial, MainActivity::class.java)
            startActivity(intent)
        }
    }
}

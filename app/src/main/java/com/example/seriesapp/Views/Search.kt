package com.example.seriesapp.Views

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.seriesapp.R

// Activity responsável por exibir a tela de pesquisa
class Search : AppCompatActivity() {

    private lateinit var workAnimationView2: LottieAnimationView
    private lateinit var workTextView2: TextView

    // Método chamado quando a atividade é criada
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.searchm) // Define o layout da atividade

        // Inicializar views
        workAnimationView2 = findViewById(R.id.work2)
        workTextView2 = findViewById(R.id.worktext2)

        // Exibir o LottieAnimationView e o TextView
        showWorkAnimation2()

        // Configurar o clique no botão de retorno
        val backbottom = findViewById<ImageView>(R.id.backbottom)
        backbottom.setOnClickListener {
            onBackPressed() // Voltar para a atividade anterior quando o botão é clicado
        }
    }

    // Método para exibir a animação de trabalho
    private fun showWorkAnimation2() {
        workAnimationView2.visibility = View.VISIBLE
        workTextView2.visibility = View.VISIBLE
        workAnimationView2.playAnimation() // Inicia a animação do Lottie
    }

    // Método para esconder a animação de trabalho
    private fun hideWorkAnimation() {
        workAnimationView2.visibility = View.GONE
        workTextView2.visibility = View.GONE
        workAnimationView2.cancelAnimation() // Cancela a animação do Lottie
    }

    // Método chamado quando a atividade é retomada
    override fun onResume() {
        super.onResume()
        // Iniciar a animação do Lottie quando a atividade estiver visível
        workAnimationView2.playAnimation()
        workAnimationView2.loop(true) // Configura para repetir a animação em loop
    }
}

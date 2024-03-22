package com.example.seriesapp.Views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.example.seriesapp.R

// Fragmento responsável por exibir a tela de itens salvos
class SavedFragment : Fragment() {

    private lateinit var workAnimationView: LottieAnimationView
    private lateinit var workTextView: TextView

    // Método chamado para criar e configurar a exibição do fragmento
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla o layout do fragmento
        val view = inflater.inflate(R.layout.fragment_saved, container, false)

        // Inicializar views
        workAnimationView = view.findViewById(R.id.work)
        workTextView = view.findViewById(R.id.worktext)

        // Exibir o LottieAnimationView e o TextView
        showWorkAnimation()

        return view
    }

    // Método para exibir a animação de trabalho
    private fun showWorkAnimation() {
        workAnimationView.visibility = View.VISIBLE
        workTextView.visibility = View.VISIBLE
        workAnimationView.playAnimation() // Inicia a animação do Lottie
    }

    // Método para esconder a animação de trabalho
    private fun hideWorkAnimation() {
        workAnimationView.visibility = View.GONE
        workTextView.visibility = View.GONE
        workAnimationView.cancelAnimation() // Cancela a animação do Lottie
    }

    // Método chamado quando o fragmento fica visível na tela
    override fun onResume() {
        super.onResume()
        // Iniciar a animação do Lottie quando o fragmento estiver visível
        workAnimationView.playAnimation()
        workAnimationView.loop(true) // Configura para repetir a animação em loop
    }

    // Método chamado quando o fragmento é pausado
    override fun onPause() {
        super.onPause()
        // Pausar a animação do Lottie quando o fragmento for pausado
        hideWorkAnimation()
    }
}

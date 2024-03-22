package com.example.seriesapp.Views

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.seriesapp.R
import com.example.seriesapp.Retrofit.Constantes
import com.example.seriesapp.Retrofit.FilmeModel

// Classe responsável por exibir os detalhes de um filme
class DetalheFilme : AppCompatActivity() {

    // Declaração das Views
    private lateinit var titleTextView: TextView
    private lateinit var descricaoTextView: TextView
    private lateinit var posterImageView: ImageView
    private lateinit var posterBack: ImageView
    private lateinit var dataLancamentoTextView: TextView
    private lateinit var starRateTextView: TextView
    private var isMostView: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalhe_filmes) // Define o layout a ser exibido

        // Obtém a referência do botão de retorno
        val backbottom = findViewById<ImageView>(R.id.backbottom)

        // Define um ouvinte de clique para o botão de retorno
        backbottom.setOnClickListener {
            onBackPressed() // Volta para a atividade anterior
        }

        // Inicializa as Views
        titleTextView = findViewById(R.id.tituloFilme)
        descricaoTextView = findViewById(R.id.descricaofilme)
        posterImageView = findViewById(R.id.posterimg)
        posterBack = findViewById(R.id.backgroundimg)
        dataLancamentoTextView = findViewById(R.id.calendartext)
        starRateTextView = findViewById(R.id.startext)

        // Recupera os dados do Intent
        val filme = intent.getSerializableExtra("filme") as? FilmeModel
        isMostView = intent.getBooleanExtra("isMostView", false)

        // Verifica se o filme não é nulo
        if (filme != null) {
            // Atualiza a interface do usuário com os dados do filme
            titleTextView.text = filme.nomeFilme
            descricaoTextView.text = filme.descricao
            dataLancamentoTextView.text = filme.dataLancamento
            starRateTextView.text = filme.starRate

            // Usa o Glide para carregar a imagem do poster e do poster de fundo
            Glide.with(this)
                .load("${Constantes.Base_img}${filme.poster}")
                .into(posterImageView)
            Glide.with(this)
                .load("${Constantes.Base_img}${filme.posterback}")
                .into(posterBack)
        } else {
            finish() // Encerra a atividade se o filme for nulo
        }
    }
}

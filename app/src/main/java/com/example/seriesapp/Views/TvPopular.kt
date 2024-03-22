package com.example.seriesapp.Views

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.seriesapp.Adapter.PopularTvAdapter
import com.example.seriesapp.R
import com.example.seriesapp.databinding.TvpopularBinding

// Atividade que exibe as séries populares atualmente
class TvPopular : AppCompatActivity() {

    private lateinit var binding: TvpopularBinding
    private lateinit var viewModel: TvShowViewModel
    private lateinit var nowPlayingAdapter: PopularTvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TvpopularBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar o clique no ícone de pesquisa
        val searchImageView = findViewById<ImageView>(R.id.search0)
        searchImageView.setOnClickListener {
            val intent = Intent(this, Search::class.java)
            startActivity(intent) // Iniciar a atividade de pesquisa ao clicar no ícone de pesquisa
        }

        // Configurar o clique no botão de retorno
        val backbottom = findViewById<ImageView>(R.id.backbottom)
        backbottom.setOnClickListener {
            onBackPressed() // Voltar para a atividade anterior ao clicar no botão de retorno
        }

        // Inicializar o ViewModel
        viewModel = ViewModelProvider(this)[TvShowViewModel::class.java]
        setupRecyclerView() // Configurar o RecyclerView para exibir as séries populares

        // Observar a lista de séries populares atualmente e atualizar o adaptador do RecyclerView
        viewModel.listNowPlaying.observe(this) { filmesNowPlaying ->
            nowPlayingAdapter.listaFilme = filmesNowPlaying
            nowPlayingAdapter.notifyDataSetChanged()
        }

        // Chamar o método correto para obter a lista de séries populares atualmente
        viewModel.nowPlayi()
    }

    // Configurar o RecyclerView para exibir as séries populares atualmente
    private fun setupRecyclerView() {
        val layoutManagerNowPlaying = GridLayoutManager(this, 2) // Layout de grade com 2 colunas
        binding.recyclerViewNewFilme.layoutManager = layoutManagerNowPlaying
        nowPlayingAdapter = PopularTvAdapter(
            this,
            listaFilme = listOf(), // Inicializa com uma lista vazia ou com dados iniciais, se houver
            tvShowViewModel = viewModel, // Passa o ViewModel para o adaptador
            isMostView = true // Indica que é uma lista de séries mais vistas
        )
        binding.recyclerViewNewFilme.adapter = nowPlayingAdapter // Define o adaptador para o RecyclerView
    }
}

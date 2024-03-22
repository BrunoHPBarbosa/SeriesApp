package com.example.seriesapp.Views

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.seriesapp.Adapter.DiscoverTvAdapter
import com.example.seriesapp.R
import com.example.seriesapp.databinding.DicovertvBinding

// Classe responsável por exibir a lista de programas de TV mais populares
class DiscoverTv : AppCompatActivity() {

    private lateinit var binding: DicovertvBinding
    private lateinit var viewModel: TvShowViewModel
    private lateinit var mostViewRAdapter: DiscoverTvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DicovertvBinding.inflate(layoutInflater)
        setContentView(binding.root) // Define o layout a ser exibido

        // Obtém a referência da imagem de pesquisa e define um ouvinte de clique para ela
        val searchImageView = findViewById<ImageView>(R.id.search0)
        searchImageView.setOnClickListener {
            val intent = Intent(this, Search::class.java)
            startActivity(intent) // Inicia a atividade de pesquisa
        }

        // Obtém a referência do botão de retorno e define um ouvinte de clique para ele
        val backbottom = findViewById<ImageView>(R.id.backbottom)
        backbottom.setOnClickListener {
            onBackPressed() // Volta para a atividade anterior
        }

        // Inicializa o ViewModel
        viewModel = ViewModelProvider(this).get(TvShowViewModel::class.java)

        // Configura o RecyclerView
        setupRecyclerView()

        // Observa a lista de programas de TV mais populares
        viewModel.listMostViewFilm.observe(this) { filmesMostView ->
            mostViewRAdapter.listaFilme = filmesMostView // Atualiza a lista de filmes
            mostViewRAdapter.notifyDataSetChanged() // Notifica o adaptador sobre a mudança nos dados
        }

        // Chama a função para obter a lista de programas de TV mais populares
        viewModel.obterListaMostView()
    }

    // Função para configurar o RecyclerView
    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(this, 2) // Define o layout do RecyclerView
        binding.recyclerViewmostview.layoutManager = layoutManager // Define o layout manager
        mostViewRAdapter = DiscoverTvAdapter(this, listOf()) // Inicializa o adaptador com uma lista vazia
        binding.recyclerViewmostview.adapter = mostViewRAdapter // Define o adaptador para o RecyclerView
    }
}

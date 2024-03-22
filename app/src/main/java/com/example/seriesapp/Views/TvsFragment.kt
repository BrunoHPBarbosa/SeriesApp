package com.example.seriesapp.Views

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.seriesapp.Adapter.GridAdapter
import com.example.seriesapp.R

// Fragmento responsável por exibir uma GridView com diferentes categorias de programas de TV
class TvsFragment : Fragment() {

    // Método chamado quando a view do fragmento é criada
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar o layout do fragmento
        val view = inflater.inflate(R.layout.fragment_tvs, container, false)

        // Inicializar a GridView que mostrará as categorias de programas de TV
        val gridView = view.findViewById<GridView>(R.id.gridfilmes)

        // Configurar o clique no ícone de pesquisa
        val searchImageView = view.findViewById<ImageView>(R.id.search0)
        searchImageView.setOnClickListener {
            val intent = Intent(requireContext(), SearchView::class.java)
            startActivity(intent) // Iniciar a atividade de pesquisa quando o ícone de pesquisa é clicado
        }

        // Lista de nomes de categorias de programas de TV
        val text = listOf("Movie Tv", "News", "Sports", "Music", "Entreteniment", "Novels", "History", "Discovery")
        // Lista de cores associadas a cada categoria
        val cor = listOf(
            R.color.cor1,
            R.color.cor2,
            R.color.cor3,
            R.color.cor4,
            R.color.cor5,
            R.color.cor6,
            R.color.cor7,
            R.color.cor8
        )
        // Inicializar o adaptador da GridView com os dados das categorias e cores correspondentes
        val adapter = GridAdapter(requireContext(), text, cor)
        gridView.adapter = adapter

        return view // Retornar a view do fragmento
    }
}

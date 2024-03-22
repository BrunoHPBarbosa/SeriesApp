package com.example.seriesapp.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.seriesapp.Retrofit.Constantes
import com.example.seriesapp.Views.DetalheFilme
import com.example.seriesapp.Retrofit.FilmeModel
import com.example.seriesapp.Views.TvShowViewModel
import com.example.seriesapp.R

class PopularTvAdapter(
    private val context: Context, // Contexto da atividade ou fragmento que está usando o adaptador
    var listaFilme: List<FilmeModel>, // Lista de objetos FilmeModel a serem exibidos no RecyclerView
    private val tvShowViewModel: TvShowViewModel, // ViewModel associado à atividade ou fragmento
    private val isMostView: Boolean // Booleano indicando se é uma lista de filmes mais visualizados ou não
) : RecyclerView.Adapter<PopularTvAdapter.ViewHolder>() {

    // Método chamado quando o RecyclerView precisa de um novo ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Infla o layout do item do RecyclerView
        val view = LayoutInflater.from(context).inflate(R.layout.cardfilmes1, parent, false)
        return ViewHolder(view) // Retorna o ViewHolder recém-criado
    }

    // Método chamado quando o RecyclerView precisa exibir os dados em uma determinada posição
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filme = listaFilme[position] // Obtém o objeto FilmeModel na posição atual

        // Carrega a imagem do filme usando Glide e define-a no ImageView do ViewHolder
        Glide.with(context).load("${Constantes.Base_img}${filme.poster}").into(holder.poster)

        // Define o título, a pontuação e o ano do filme nos TextViews do ViewHolder
        holder.tituloTextView.text = filme.nomeFilme
        holder.estrelaTextView.text = filme.starRate
        holder.anoTextView.text = filme.dataLancamento

        // Define a visibilidade do ProgressBar com base no status de carregamento do filme
        if (filme.isLoading) {
            holder.progressBar.visibility = View.VISIBLE
        } else {
            holder.progressBar.visibility = View.GONE
        }

        // Configura um listener de clique para o CardView do ViewHolder
        holder.card.setOnClickListener {
            // Registra no log o filme selecionado
            Log.d("PopularTvAdapter", "Filme selecionado: $filme")
            // Cria um intent para abrir a atividade DetalheFilme, passando os dados do filme
            val intent = Intent(context, DetalheFilme::class.java)
            intent.putExtra("filme", filme)
            context.startActivity(intent) // Inicia a atividade DetalheFilme
        }
    }

    // Retorna o número total de itens na lista de filmes
    override fun getItemCount(): Int {
        return listaFilme.size
    }

    // Método para atualizar a lista de filmes e notificar o RecyclerView para atualizar a exibição
    fun updateListaFilme(newList: List<FilmeModel>) {
        listaFilme = newList
        notifyDataSetChanged() // Notifica o RecyclerView para atualizar a exibição
    }

    // Classe ViewHolder que representa cada item na exibição do RecyclerView
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Declaração das visualizações dentro do layout do item
        val card: CardView = itemView.findViewById(R.id.cardfilm1)
        val poster: ImageView = itemView.findViewById(R.id.filmposter)
        val tituloTextView: TextView = itemView.findViewById(R.id.filmname)
        val estrelaTextView: TextView = itemView.findViewById(R.id.starscore)
        val anoTextView: TextView = itemView.findViewById(R.id.anofilme)
        val progressBar: ProgressBar = itemView.findViewById(R.id.load3)
    }
}

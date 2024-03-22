package com.example.seriesapp.Adapter

import android.content.Context
import android.content.Intent
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

class TrendingTvDayAdapter(
    private val context: Context, // Contexto da atividade ou fragmento que está usando o adaptador
    var listaFilme: List<FilmeModel> = emptyList(), // Lista de filmes a serem exibidos no RecyclerView
    private val tvShowViewModel: TvShowViewModel // ViewModel associado a esta adaptação
) : RecyclerView.Adapter<TrendingTvDayAdapter.ViewHolder>() {

    // Método chamado quando o RecyclerView precisa de um novo ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Infla o layout do item de filme de acordo com o tipo de visualização
        val view = LayoutInflater.from(context).inflate(R.layout.cardsfilmes2, parent, false)
        // Retorna um novo ViewHolder associado ao layout do item de filme
        return ViewHolder(view, context, listaFilme, tvShowViewModel)
    }

    // Método chamado quando o RecyclerView precisa exibir os dados em uma posição específica
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Liga o filme da posição atual ao ViewHolder
        holder.bind(listaFilme[position])
    }

    // Método chamado para obter o número total de itens na lista de filmes
    override fun getItemCount(): Int {
        return listaFilme.size
    }

    // Método para atualizar a lista de filmes exibidos no RecyclerView
    fun updateListaFilme(newList: List<FilmeModel>) {
        listaFilme = newList
        notifyDataSetChanged()
    }

    // ViewHolder que contém as visualizações necessárias para exibir um item de filme no RecyclerView
    class ViewHolder(
        itemView: View,
        private val context: Context,
        private val listaFilme: List<FilmeModel>,
        private val tvShowViewModel: TvShowViewModel
    ) : RecyclerView.ViewHolder(itemView) {
        // Inicialização das visualizações do item de filme
        val card = itemView.findViewById(R.id.cardfilm2) as CardView
        val poster = itemView.findViewById(R.id.filmeposter2) as ImageView
        val tituloTextView = itemView.findViewById(R.id.filmname2) as TextView
        val estrelaTextView = itemView.findViewById(R.id.starscore2) as TextView
        val progressBar1 = itemView.findViewById(R.id.load1) as ProgressBar

        init {
            // Configura o clique no card para iniciar a atividade DetalheFilme com os detalhes do filme
            card.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val filme = listaFilme[position]
                    val intent = Intent(context, DetalheFilme::class.java)
                    intent.putExtra("filme", filme)
                    context.startActivity(intent)
                }
            }
        }

        // Método para vincular os dados do filme ao ViewHolder
        fun bind(filme: FilmeModel) {
            // Usa Glide ou outras bibliotecas para carregar a imagem do filme no ImageView
            Glide
                .with(context)
                .load("${Constantes.Base_img}${filme.posterback}")
                .centerCrop()
                .into(poster)

            // Configura outros dados do filme, como título e classificação
            tituloTextView.text = filme.nomeFilme
            estrelaTextView.text = filme.starRate

            // Define a visibilidade do ProgressBar com base no estado de carregamento do filme
            progressBar1.visibility = if (filme.isLoading) View.VISIBLE else View.GONE
        }
    }
}

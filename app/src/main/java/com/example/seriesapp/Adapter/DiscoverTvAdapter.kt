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
import com.example.seriesapp.R

class DiscoverTvAdapter(
    private val context: Context,
    var listaFilme: List<FilmeModel> = emptyList() // Lista de filmes a ser exibida inicialmente vazia
) : RecyclerView.Adapter<DiscoverTvAdapter.ViewHolder>() {

    // Método chamado quando o RecyclerView precisa criar um novo ViewHolder para exibir um item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Infla o layout do item de filme
        val view = LayoutInflater.from(context).inflate(R.layout.cardfilmes1, parent, false)
        // Retorna um novo ViewHolder com a visualização do item de filme
        return ViewHolder(view)
    }

    // Método chamado quando o RecyclerView precisa atualizar um ViewHolder com novos dados
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Obtém o filme na posição atual da lista
        val filme = listaFilme[position]

        // Carrega a imagem do pôster do filme usando a biblioteca Glide
        Glide.with(context).load("${Constantes.Base_img}${filme.poster}").into(holder.poster)

        // Configura os TextViews no ViewHolder com os detalhes do filme
        holder.tituloTextView.text = filme.nomeFilme
        holder.estrelaTextView.text = filme.starRate
        holder.anoTextView.text = filme.dataLancamento

        // Exibe ou oculta o ProgressBar dependendo do status de carregamento do filme
        if (filme.isLoading) {
            holder.progressBar.visibility = View.VISIBLE
        } else {
            holder.progressBar.visibility = View.GONE
        }

        // Define um OnClickListener para o CardView do filme para abrir os detalhes do filme
        holder.card.setOnClickListener {
            val intent = Intent(context, DetalheFilme::class.java)
            intent.putExtra("filme", filme)
            context.startActivity(intent)
        }
    }

    // Método chamado para obter o número total de itens na lista de filmes
    override fun getItemCount(): Int {
        return listaFilme.size
    }

    // Método para atualizar a lista de filmes exibida pelo adapter com uma nova lista
    fun updateListaFilme(newList: List<FilmeModel>) {
        listaFilme = newList
        notifyDataSetChanged() // Notifica o RecyclerView que os dados foram alterados e precisa atualizar a exibição
    }

    // ViewHolder que contém as visualizações de um item de filme
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Referências para as visualizações dentro do item de filme
        val card: CardView = itemView.findViewById(R.id.cardfilm1)
        val poster: ImageView = itemView.findViewById(R.id.filmposter)
        val tituloTextView: TextView = itemView.findViewById(R.id.filmname)
        val estrelaTextView: TextView = itemView.findViewById(R.id.starscore)
        val anoTextView: TextView = itemView.findViewById(R.id.anofilme)
        val progressBar: ProgressBar = itemView.findViewById(R.id.load3)
    }
}

package com.example.seriesapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.seriesapp.R

class GridAdapter(

    private val context: Context, // Contexto da atividade ou fragmento que está usando o adaptador
    private val text: List<String>, // Lista de texto a ser exibida nos itens do grid
    private val Cor: List<Int> // Lista de cores para os itens do grid

) : BaseAdapter() {

    // Retorna o número de itens no conjunto de dados
    override fun getCount(): Int = text.size

    // Retorna o item na posição especificada no conjunto de dados
    override fun getItem(position: Int): Any = text[position]

    // Retorna o ID do item na posição especificada no conjunto de dados
    override fun getItemId(position: Int): Long = position.toLong()

    // Método responsável por criar e retornar a exibição para cada item no conjunto de dados
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // Infla o layout do item do cartão de série se necessário, ou reutiliza a exibição convertida
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.cardserie, parent, false)

        // Obtém referências para as visualizações dentro do item do cartão de série
        val cardtext = view.findViewById<TextView>(R.id.textcardserie)
        val cardlayout = view.findViewById<CardView>(R.id.cardserie)

        // Define o texto e a cor de fundo do cartão com base nas listas fornecidas
        cardtext.text = text[position]
        cardlayout.setCardBackgroundColor(ContextCompat.getColor(context, Cor[position]))

        return view // Retorna a exibição do item do cartão de série atualizada
    }
}

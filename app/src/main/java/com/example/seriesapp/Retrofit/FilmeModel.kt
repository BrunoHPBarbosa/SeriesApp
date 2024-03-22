package com.example.seriesapp.Retrofit

import com.google.gson.annotations.SerializedName
import java.io.Serializable

// Classe de modelo para representar os detalhes de um filme
data class FilmeModel (
    @SerializedName("id")
    var id:String, // ID único do filme

    @SerializedName("original_name")
    var nomeFilme:String, // Nome original do filme

    @SerializedName("overview")
    var descricao:String, // Descrição ou resumo do filme

    @SerializedName("poster_path")
    var poster:String, // Caminho para o pôster do filme

    @SerializedName("backdrop_path")
    var posterback:String, // Caminho para o pôster de fundo do filme

    @SerializedName("first_air_date")
    var dataLancamento:String, // Data de lançamento do filme

    @SerializedName("vote_average")
    var starRate:String, // Classificação média do filme

    @SerializedName("vote_count")
    var voto:String, // Contagem de votos do filme

    val isLoading: Boolean // Flag para indicar se o filme está sendo carregado
): Serializable {
}

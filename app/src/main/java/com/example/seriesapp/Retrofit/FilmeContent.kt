package com.example.seriesapp.Retrofit

import com.google.gson.annotations.SerializedName

// Classe de modelo para encapsular os resultados da resposta da API de filmes
data class FilmeContent(
    @SerializedName("results")
    var resultado: List<FilmeModel> // Lista de filmes obtidos da API
)

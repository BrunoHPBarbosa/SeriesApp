package com.example.seriesapp.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {
    // Cria uma instância Retrofit usando a baseUrl e o GsonConverterFactory
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constantes.BASE_URL) // Define a URL base para todas as chamadas de API
            .addConverterFactory(GsonConverterFactory.create()) // Usa o Gson para converter JSON em objetos Kotlin
            .build()
    }

    // Cria uma instância do serviço de filmes usando a Retrofit criada anteriormente
    val filmeService: FilmeService by lazy {
        retrofit.create(FilmeService::class.java) // Cria uma implementação do serviço FilmeService
    }
}

package com.example.seriesapp.Retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmeService {
    // Define um método para obter os filmes em destaque no dia
    @GET("trending/tv/day?language=en-US")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String // Parâmetro de consulta para a chave da API
    ): Response<FilmeContent> // Retorna uma resposta de FilmeContent

    // Define um método para obter os filmes populares
    @GET("tv/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String // Parâmetro de consulta para a chave da API
    ): Response<FilmeContent> // Retorna uma resposta de FilmeContent

    // Define um método para obter os filmes em exibição atualmente
    @GET("discover/tv")
    suspend fun getNowPlay(
        @Query("api_key") apiKey: String // Parâmetro de consulta para a chave da API
    ): Response<FilmeContent> // Retorna uma resposta de FilmeContent
}

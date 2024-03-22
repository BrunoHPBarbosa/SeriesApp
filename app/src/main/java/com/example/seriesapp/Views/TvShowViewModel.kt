package com.example.seriesapp.Views

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seriesapp.Retrofit.Constantes
import com.example.seriesapp.Retrofit.FilmeModel
import com.example.seriesapp.Retrofit.RetrofitConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// ViewModel responsável por gerenciar os dados relacionados aos programas de TV
class TvShowViewModel : ViewModel() {

    // LiveData para armazenar a lista de filmes mais vistos
    private val _listMostView: MutableLiveData<List<FilmeModel>> = MutableLiveData()
    val listMostViewFilm: LiveData<List<FilmeModel>> get() = _listMostView

    // LiveData para armazenar a lista de filmes em exibição atualmente
    private val _listNowPlay: MutableLiveData<List<FilmeModel>> = MutableLiveData()
    val listNowPlaying: LiveData<List<FilmeModel>> get() = _listNowPlay

    // LiveData para armazenar a lista de filmes em breve
    private val _listUpcomingMovies = MutableLiveData<List<FilmeModel>>()
    val listUpcomingMovies: LiveData<List<FilmeModel>> get() = _listUpcomingMovies

    // LiveData para indicar se a lista de filmes está sendo carregada
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        _isLoading.value = false // Inicializa como falso, indicando que não está carregando
    }

    // Função para obter a lista de filmes em breve
    fun upcomingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _isLoading.postValue(true) // Define isLoading como true durante a chamada da API

                // Chamada da API para obter os filmes em breve
                val response = RetrofitConfig.filmeService.getUpcomingMovies(Constantes.API_Key)
                withContext(Dispatchers.Main) {
                    // Processa a resposta da API na thread principal
                    response.body()?.let { filmes ->
                        // Ordena os filmes por classificação
                        _listUpcomingMovies.value = filmes.resultado.sortedByDescending { it.starRate }
                        _isLoading.value = false // Define isLoading como false após o carregamento
                    }
                }
            } catch (e: Exception) {
                // Trata exceções, por exemplo, exibe uma mensagem de erro
                e.printStackTrace()
                _isLoading.value = false
            }
        }
    }

    // Função para obter a lista de filmes em exibição atualmente
    fun nowPlayi() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _isLoading.postValue(true) // Define isLoading como true durante a chamada da API

                // Chamada da API para obter os filmes em exibição atualmente
                val response = RetrofitConfig.filmeService.getNowPlay(Constantes.API_Key)
                withContext(Dispatchers.Main) {
                    // Processa a resposta da API na thread principal
                    response.body()?.let { filmes ->
                        Log.d("NowPlaying", "Número de filmes recebidos: ${filmes.resultado.size}")
                        // Ordena os filmes por classificação
                        _listNowPlay.value = filmes.resultado.sortedByDescending { it.starRate }
                        _isLoading.value = false // Define isLoading como false após o carregamento
                    }
                }
            } catch (e: Exception) {
                // Trata exceções, por exemplo, exibe uma mensagem de erro
                e.printStackTrace()
                _isLoading.value = false
            }
        }
    }

    // Função para obter a lista de filmes mais vistos
    fun obterListaMostView() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _isLoading.postValue(true) // Define isLoading como true durante a chamada da API

                // Chamada da API para obter os filmes mais vistos
                val response = RetrofitConfig.filmeService.getPopularMovies(Constantes.API_Key)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        // Processa a resposta da API na thread principal
                        response.body()?.let {
                            _listMostView.value = it.resultado
                            _isLoading.value = false // Define isLoading como false após o carregamento
                        }
                    } else {
                        // Em caso de falha na resposta da API, exibe uma mensagem de erro
                        Log.e("MostView", "Erro na resposta da API: ${response.message()}")
                        _isLoading.value = false
                    }
                }
            } catch (e: Exception) {
                // Trata exceções, por exemplo, exibe uma mensagem de erro
                e.printStackTrace()
                Log.e("MostView", "Erro na chamada da API: ${e.message}")
                _isLoading.value = false
            }
        }
    }
}

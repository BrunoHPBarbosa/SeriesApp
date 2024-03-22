package com.example.seriesapp.Views

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.seriesapp.Adapter.DiscoverTvAdapter
import com.example.seriesapp.Adapter.PopularTvAdapter
import com.example.seriesapp.Adapter.TrendingTvDayAdapter
import com.example.seriesapp.R

// Fragmento responsável por exibir a tela inicial com diferentes seções
class HomeFragment : Fragment() {

    // Variáveis ​​de RecyclerView e adaptares
    private lateinit var recyclerViewTrending: TrendingTvDayAdapter
    private lateinit var recyclerViewNowPlay: PopularTvAdapter
    private lateinit var recyclerViewMostPop: DiscoverTvAdapter
    private var currentPosition: Int = 0
    private val scrollDelay: Long = 3000 // Tempo de atraso para rolagem automática
    private lateinit var handler: Handler
    private lateinit var autoScrollRunnable: Runnable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla o layout do fragmento
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicialização de views e configuração de cliques
        val arrownewfilmes = view.findViewById<ImageView>(R.id.arrow1)
        val arrowmostview = view.findViewById<ImageView>(R.id.arrow2)

        // Configuração do clique para abrir atividades específicas
        arrownewfilmes.setOnClickListener {
            val intent = Intent(requireContext(), TvPopular::class.java)
            startActivity(intent)
        }
        arrowmostview.setOnClickListener {
            val intent = Intent(requireContext(), DiscoverTv::class.java)
            startActivity(intent)
        }

        val searchImageView = view.findViewById<ImageView>(R.id.search0)
        searchImageView.setOnClickListener {
            val intent = Intent(requireContext(), Search::class.java)
            startActivity(intent)
        }

        // Inicialização dos ViewModels
        val viewModel = ViewModelProvider(this).get(TvShowViewModel::class.java)
        val viewModel2 = ViewModelProvider(this).get(TvShowViewModel::class.java)
        val viewModel3 = ViewModelProvider(this).get(TvShowViewModel::class.java)

        // Inicialização e configuração dos RecyclerViews e adaptadores
        recyclerViewTrending = TrendingTvDayAdapter(
            requireContext(),
            listaFilme = mutableListOf(),
            tvShowViewModel = viewModel
        )
        val recyclerView1 = view.findViewById<RecyclerView>(R.id.newfilmes)
        recyclerView1.adapter = recyclerViewTrending
        recyclerView1.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        recyclerViewNowPlay = PopularTvAdapter(
            requireContext(),
            listaFilme = mutableListOf(),
            tvShowViewModel = viewModel,
            isMostView = false
        )
        val recyclerViewNP = view.findViewById<RecyclerView>(R.id.New)
        recyclerViewNP.adapter = recyclerViewNowPlay
        recyclerViewNP.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        recyclerViewMostPop = DiscoverTvAdapter(
            requireContext(),
            listaFilme = mutableListOf(),
        )
        val recyclerViewMP = view.findViewById<RecyclerView>(R.id.mostpopular)
        recyclerViewMP.adapter = recyclerViewMostPop
        recyclerViewMP.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        // Observação das listas de filmes
        viewModel.upcomingMovies()
        viewModel2.listUpcomingMovies.observe(viewLifecycleOwner) { filmesNew ->
            recyclerViewTrending.updateListaFilme(filmesNew)
        }

        viewModel.obterListaMostView()
        viewModel3.listMostViewFilm.observe(viewLifecycleOwner) { filmesMostView ->
            val cincoPrimeirosFilmesMostView = filmesMostView.take(5)
            recyclerViewMostPop.updateListaFilme(cincoPrimeirosFilmesMostView)
        }

        viewModel.nowPlayi()
        viewModel.listNowPlaying.observe(viewLifecycleOwner) { filmesNowPlaying ->
            val cincoPrimeirosFilmesNowPlaying = filmesNowPlaying.take(5)
            recyclerViewNowPlay.updateListaFilme(cincoPrimeirosFilmesNowPlaying)
        }

        // Iniciar rolagem automática
        startAutoScroll(recyclerView1)
    }

    // Método para iniciar a rolagem automática dos filmes em destaque
    private fun startAutoScroll(recyclerView: RecyclerView) {
        handler = Handler()
        autoScrollRunnable = object : Runnable {
            override fun run() {
                currentPosition++
                if (currentPosition >= recyclerViewTrending.itemCount) {
                    currentPosition = 0
                }
                recyclerView.smoothScrollToPosition(currentPosition)
                handler.postDelayed(this, scrollDelay)
            }
        }
        handler.postDelayed(autoScrollRunnable, scrollDelay)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Limpa todos os callbacks para evitar vazamentos de memória
        handler.removeCallbacks(autoScrollRunnable)
    }
}

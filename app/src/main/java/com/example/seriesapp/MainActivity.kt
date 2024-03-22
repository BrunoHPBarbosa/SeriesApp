package com.example.seriesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.seriesapp.Views.HomeFragment
import com.example.seriesapp.Views.SavedFragment
import com.example.seriesapp.Views.SeriesFragment
import com.example.seriesapp.Views.TvsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

private lateinit var bottomNavigationView: BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referência para o BottomNavigationView no layout
        bottomNavigationView = findViewById(R.id.botton_navigation_view)

        // Define um ouvinte para os itens de navegação do BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            // Determina qual fragmento exibir com base no item de menu selecionado
            when (menuItem.itemId) {
                R.id.menu_item1 -> {
                    replaceFragment(HomeFragment()) // Exibe o fragmento HomeFragment
                    true
                }
                R.id.menu_item2 -> {
                    replaceFragment(SeriesFragment()) // Exibe o fragmento SeriesFragment
                    true
                }
                R.id.menu_item3 -> {
                    replaceFragment(TvsFragment()) // Exibe o fragmento TvsFragment
                    true
                }
                R.id.menu_item4 -> {
                    replaceFragment(SavedFragment()) // Exibe o fragmento SavedFragment
                    true
                }
                else -> false
            }
        }

        // Se a instância salva não for nula, nenhum novo fragmento será adicionado
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment()) // Exibe o fragmento HomeFragment por padrão
        }
    }

    // Método para substituir o fragmento atual pelo fornecido
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame, fragment).commit()
        // R.id.frame é o ID do contêiner onde os fragmentos serão exibidos na atividade
    }
}

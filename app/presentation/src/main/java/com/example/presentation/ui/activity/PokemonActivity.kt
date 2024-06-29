package com.example.presentation.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.presentation.ui.navigation.PokemonNavHost
import com.example.presentation.ui.pages.pokemon_list.PokemonList
import com.example.presentation.ui.theme.PokemonsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonsTheme {
                val navController = rememberNavController()

                PokemonNavHost(navController = navController, startDestination = PokemonList)
            }
        }
    }
}

package com.example.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.presentation.ui.pages.pokemon_details.PokemonDetailsScreen
import com.example.presentation.ui.pages.pokemon_details.PokemonDetails
import com.example.presentation.ui.pages.pokemon_list.PokemonListScreen
import com.example.presentation.ui.pages.pokemon_list.PokemonList

@Composable
fun PokemonNavHost(navController: NavHostController, startDestination: Any) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable<PokemonList> {
            PokemonListScreen(navController = navController)
        }

        composable<PokemonDetails> { entry ->
            PokemonDetailsScreen(pokemonDetails = entry.toRoute())
        }
    }
}

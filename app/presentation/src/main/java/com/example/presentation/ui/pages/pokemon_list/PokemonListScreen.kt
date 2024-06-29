package com.example.presentation.ui.pages.pokemon_list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.serialization.Serializable

@Serializable
object PokemonList

@Composable
fun PokemonListScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<PokemonListViewModel>()
}

@Composable
private fun SuccessScreen(modifier: Modifier = Modifier) {

}

@Composable
private fun LoadingScreen(modifier: Modifier = Modifier) {

}

@Composable
private fun ErrorScreen(modifier: Modifier = Modifier) {

}

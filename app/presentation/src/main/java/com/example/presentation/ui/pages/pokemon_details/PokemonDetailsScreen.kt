package com.example.presentation.ui.pages.pokemon_details

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetails(
    val pokemonName: String,
    val pokemonImageUrl: String
)

@Composable
fun PokemonDetailsScreen(pokemonDetails: PokemonDetails) {

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

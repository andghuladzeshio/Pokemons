package com.example.presentation.ui.pages.pokemon_details

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

@Composable
fun PokemonDetailsScreen(pokemonDetails: PokemonDetails) {

}

@Serializable
data class PokemonDetails(
    val pokemonName: String,
    val pokemonImageUrl: String
)

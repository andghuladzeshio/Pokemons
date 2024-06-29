package com.example.domain.model

data class PokemonsResponse(
    val hasNext: Boolean,
    val pokemons: List<Pokemon>
)
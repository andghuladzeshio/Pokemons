package com.example.data.model

data class PokemonsResponseEntity(
    val next: String?,
    val results: List<PokemonEntity>
)
package com.example.data.datasource

import com.example.data.api.PokemonApi
import com.example.data.api.PokemonImageApi
import javax.inject.Inject

class PokemonDatasourceImpl @Inject constructor(
    private val pokemonApi: PokemonApi,
    private val pokemonImageApi: PokemonImageApi
): PokemonDatasource {
}
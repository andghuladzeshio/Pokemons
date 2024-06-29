package com.example.data.datasource

import com.example.data.api.PokemonApi
import com.example.data.api.PokemonImageApi
import com.example.data.model.PokemonsResponseEntity
import javax.inject.Inject

class PokemonDatasourceImpl @Inject constructor(
    private val pokemonApi: PokemonApi,
    private val pokemonImageApi: PokemonImageApi
): PokemonDatasource {

    override suspend fun getPokemons(offset: Int, limit: Int): PokemonsResponseEntity {
        return pokemonApi.getPokemons(offset, limit)
    }
}
package com.example.data.datasource

import com.example.data.model.PokemonsResponseEntity

interface PokemonDatasource {

    suspend fun getPokemons(offset: Int, limit: Int): PokemonsResponseEntity

}
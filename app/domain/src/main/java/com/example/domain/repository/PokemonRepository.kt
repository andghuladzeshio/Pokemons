package com.example.domain.repository

import com.example.domain.model.PokemonsResponse

interface PokemonRepository {

    suspend fun getPokemons(offset: Int, limit: Int): PokemonsResponse

}
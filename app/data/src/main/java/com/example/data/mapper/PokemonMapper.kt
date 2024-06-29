package com.example.data.mapper

import com.example.data.model.PokemonsResponseEntity
import com.example.domain.model.PokemonsResponse

interface PokemonMapper {

    fun mapToPokemonsResponse(pokemonsResponseEntity: PokemonsResponseEntity): PokemonsResponse

}
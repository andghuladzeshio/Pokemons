package com.example.data.mapper

import com.example.data.model.PokemonEntity
import com.example.data.model.PokemonsResponseEntity
import com.example.domain.model.Pokemon
import com.example.domain.model.PokemonsResponse
import com.example.shared.BuildConfig
import javax.inject.Inject

class PokemonMapperImpl @Inject constructor() : PokemonMapper {

    override fun mapToPokemonsResponse(pokemonsResponseEntity: PokemonsResponseEntity): PokemonsResponse {
        return PokemonsResponse(
            hasNext = pokemonsResponseEntity.next != null,
            pokemons = pokemonsResponseEntity.results.map { entity ->
                mapToPokemon(entity)
            }
        )
    }

    private fun mapToPokemon(pokemonEntity: PokemonEntity): Pokemon {
        return Pokemon(
            pokemonEntity.name,
            BuildConfig.API_URL_IMAGE.plus(pokemonEntity.name).plus(IMAGE_EXTENSION)
        )
    }

    companion object {
        private const val IMAGE_EXTENSION = ".jpg"
    }
}
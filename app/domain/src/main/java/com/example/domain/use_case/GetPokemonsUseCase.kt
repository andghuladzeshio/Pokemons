package com.example.domain.use_case

import com.example.domain.model.PokemonsResponse
import com.example.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(offset: Int, limit: Int): PokemonsResponse {
        return repository.getPokemons(offset, limit)
    }

}
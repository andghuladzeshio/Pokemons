package com.example.data.repository

import com.example.data.datasource.PokemonDatasource
import com.example.data.mapper.PokemonMapper
import com.example.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val datasource: PokemonDatasource,
    private val mapper: PokemonMapper
): PokemonRepository {
}
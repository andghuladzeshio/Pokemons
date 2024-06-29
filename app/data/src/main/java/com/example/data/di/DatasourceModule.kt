package com.example.data.di

import com.example.data.datasource.PokemonDatasource
import com.example.data.datasource.PokemonDatasourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatasourceModule {

    @Binds
    @Singleton
    abstract fun bindPokemonDataSource(pokemonDatasourceImpl: PokemonDatasourceImpl): PokemonDatasource

}
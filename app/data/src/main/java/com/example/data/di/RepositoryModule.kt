package com.example.data.di

import com.example.data.mapper.PokemonMapper
import com.example.data.mapper.PokemonMapperImpl
import com.example.domain.repository.PokemonRepository
import com.example.data.repository.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindPokemonMapper(mapperImpl: PokemonMapperImpl): PokemonMapper

    @Singleton
    @Binds
    abstract fun bindPokemonRepository(repository: PokemonRepositoryImpl): PokemonRepository

}
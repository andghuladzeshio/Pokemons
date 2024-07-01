package com.example.presentation.ui.pages.pokemon_list.di

import com.example.domain.model.Pokemon
import com.example.shared.core.ui.util.pagination.PaginationHelper
import com.example.shared.core.ui.util.pagination.PaginationHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PokemonListScreenModule {

    @Binds
    abstract fun bindPaginationHelper(helperImpl: PaginationHelperImpl<Pokemon>): PaginationHelper<Pokemon>
}
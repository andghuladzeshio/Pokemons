package com.example.data.di

import com.example.data.api.PokemonApi
import com.example.data.api.PokemonImageApi
import com.example.shared.core.network.di.ApiClient
import com.example.shared.core.network.di.ImageClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun providePokemonApi(@ApiClient retrofit: Retrofit): PokemonApi {
        return retrofit.create(PokemonApi::class.java)
    }

    @Provides
    @Singleton
    fun providePokemonImageApi(@ImageClient retrofit: Retrofit): PokemonImageApi {
        return retrofit.create(PokemonImageApi::class.java)
    }

}
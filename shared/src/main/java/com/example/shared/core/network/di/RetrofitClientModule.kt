package com.example.shared.core.network.di

import com.example.shared.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitClientModule {

    @Provides
    @Singleton
    @ApiClient
    fun provideRetrofitClient(): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @ImageClient
    fun provideRetrofitImageClient(): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.API_URL_IMAGE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
package com.example.nasapictureofdaymvvm.di

import com.example.nasapictureofdaymvvm.data.repository.NasaRepositoryImpl
import com.example.nasapictureofdaymvvm.domain.NasaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindNasaRepository(
        nasaRepositoryImpl: NasaRepositoryImpl
    ): NasaRepository
}
package com.example.viajes.di

import com.example.viajes.data.repository.DestinoRepository
import com.example.viajes.data.repository.FakeDestinoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDestinoRepository(): DestinoRepository {
        return FakeDestinoRepositoryImpl()
    }
}
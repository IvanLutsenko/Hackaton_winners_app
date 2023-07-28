package com.example.hackathonwinnersapp.di

import com.example.hackathonwinnersapp.data.repository.RepositoryImpl
import com.example.hackathonwinnersapp.domain.repositories.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindRepository(repositoryImpl: RepositoryImpl): Repository
}
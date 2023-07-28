package com.example.hackathonwinnersapp.di

import com.example.hackathonwinnersapp.data.repository.OrdersRepositoryImpl
import com.example.hackathonwinnersapp.domain.repositories.OrdersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindOrdersRepository(ordersRepositoryImpl: OrdersRepositoryImpl): OrdersRepository
}
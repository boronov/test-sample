package ru.appsmile.test.hotel.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.appsmile.test.hotel.data.remote.NetworkClient
import ru.appsmile.test.hotel.data.remote.provideDataLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideLoggingInterceptor() = provideDataLoggingInterceptor()

    @Singleton
    @Provides
    fun provideUserApiService(
        networkClient: NetworkClient
    ) = networkClient.provideHotelApiService()
}
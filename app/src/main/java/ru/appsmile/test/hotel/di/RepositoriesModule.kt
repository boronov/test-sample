package ru.appsmile.test.hotel.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.appsmile.test.hotel.data.repository.HotelRepositoryImpl
import ru.appsmile.test.hotel.domain.repository.HotelRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {
    @Binds
    @Singleton
    abstract fun bindHotelRepository(
        repositoryImpl: HotelRepositoryImpl
    ): HotelRepository

}
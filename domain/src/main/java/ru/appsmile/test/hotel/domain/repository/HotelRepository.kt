package ru.appsmile.test.hotel.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.appsmile.test.hotel.domain.Resource
import ru.appsmile.test.hotel.domain.model.BookingInfo
import ru.appsmile.test.hotel.domain.model.Hotel
import ru.appsmile.test.hotel.domain.model.Room

interface HotelRepository {
    suspend fun getHotel(): Flow<Resource<Hotel>>

    suspend fun getHotelRooms(): Flow<Resource<List<Room>>>

    suspend fun getBookingInfo(): Flow<Resource<BookingInfo>>
}
package ru.appsmile.test.hotel.data.remote.apiservices

import retrofit2.http.GET
import ru.appsmile.test.hotel.data.remote.dtos.BookingInfoDto
import ru.appsmile.test.hotel.data.remote.dtos.HotelDto
import ru.appsmile.test.hotel.data.remote.dtos.RoomsDto

interface HotelApiService {
    @GET("d144777c-a67f-4e35-867a-cacc3b827473")
    suspend fun getHotel(): HotelDto

    @GET("8b532701-709e-4194-a41c-1a903af00195")
    suspend fun getHotelRooms(): RoomsDto

    @GET("63866c74-d593-432c-af8e-f279d1a8d2ff")
    suspend fun getBookingInfo(): BookingInfoDto
}
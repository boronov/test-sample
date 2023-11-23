package ru.appsmile.test.hotel.data.repository

import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ru.appsmile.test.hotel.data.remote.apiservices.HotelApiService
import ru.appsmile.test.hotel.data.remote.dtos.toDomain
import ru.appsmile.test.hotel.domain.Resource
import ru.appsmile.test.hotel.domain.repository.HotelRepository
import java.io.IOException
import javax.inject.Inject

class HotelRepositoryImpl @Inject constructor(
    private val apiService: HotelApiService
) : HotelRepository {
    override suspend fun getHotel() = flow {
        try {
            emit(Resource.Loading())
            val hotelItem = apiService.getHotel().toDomain()
            emit(Resource.Success(hotelItem))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    "Something went wrong"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    "Something went wrong"
                )
            )
        }
    }

    override suspend fun getHotelRooms() = flow {
        try {
            emit(Resource.Loading())
            val items = apiService.getHotelRooms().rooms.map { it.toDomain() }
            emit(Resource.Success(items))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    "Something went wrong"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    "Something went wrong"
                )
            )
        }
    }

    override suspend fun getBookingInfo() = flow {
        try {
            emit(Resource.Loading())
            val info = apiService.getBookingInfo().toDomain()
            emit(Resource.Success(info))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    "Something went wrong"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    "Something went wrong"
                )
            )
        }
    }
}
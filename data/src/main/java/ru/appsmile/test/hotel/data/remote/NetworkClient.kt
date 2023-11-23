package ru.appsmile.test.hotel.data.remote

import okhttp3.logging.HttpLoggingInterceptor
import ru.appsmile.test.hotel.data.remote.apiservices.HotelApiService
import javax.inject.Inject

class NetworkClient @Inject constructor(
    loggingInterceptor: HttpLoggingInterceptor,
) {

    private val provideRetrofit = provideRetrofit(
        provideOkHttpClientBuilder().apply {
            addInterceptor(loggingInterceptor)
        }.build()
    )

    fun provideHotelApiService(): HotelApiService = provideRetrofit.create(
        HotelApiService::class.java
    )
}
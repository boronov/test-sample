package ru.appsmile.test.hotel.domain.model

import java.util.Date

data class BookingInfo(
    val id: Int,
    val hotelName: String,
    val hotelAddress: String,
    val hotelRating: Double,
    val ratingName: String,
    val departure: String,
    val arrivalCountry: String,
    val tourDateStart: Date,
    val tourDateStop: Date,
    val numberOfNights: Int,
    val room: String,
    val nutrition: String,
    val tourPrice: Double,
    val fuelCharge: Double,
    val serviceCharge: Double
)

package ru.appsmile.test.hotel.domain.model

data class BookingInfo(
    val id: Int,
    val hotelName: String,
    val hotelAddress: String,
    val hotelRating: Double,
    val ratingName: String,
    val departure: String,
    val arrivalCountry: String,
    val tourDateStart: String,
    val tourDateStop: String,
    val numberOfNights: Int,
    val room: String,
    val nutrition: String,
    val tourPrice: Double,
    val fuelCharge: Double,
    val serviceCharge: Double
)

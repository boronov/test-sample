package ru.appsmile.test.hotel.domain.model

data class Hotel(
    val id: Int,
    val name: String,
    val address: String,
    val minimalPrice: Double,
    val priceForIt: String,
    val rating: Double,
    val ratingName: String,
    val imageUrls: List<String>,
    val aboutTheHotel: About

) {
    data class About(
        val description: String,
        val peculiarities: List<String>
    )
}

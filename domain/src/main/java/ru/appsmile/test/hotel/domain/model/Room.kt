package ru.appsmile.test.hotel.domain.model

data class Room(
    val id: Int,
    val name: String,
    val price: Double,
    val pricePer: String,
    val peculiarities: List<String>,
    val imageUrls: List<String>
)

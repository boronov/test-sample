package ru.appsmile.test.hotel.data.remote.dtos

import com.squareup.moshi.Json
import ru.appsmile.test.hotel.domain.model.Hotel

data class HotelDto(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "adress") val address: String,
    @field:Json(name = "minimal_price") val minimalPrice: Double,
    @field:Json(name = "price_for_it") val priceForIt: String,
    @field:Json(name = "rating") val rating: Double,
    @field:Json(name = "rating_name") val ratingName: String,
    @field:Json(name = "image_urls") val imageUrls: List<String>,
    @field:Json(name = "about_the_hotel") val aboutTheHotel: About

) {
    data class About(
        @field:Json(name = "description") val description: String,
        @field:Json(name = "peculiarities") val peculiarities: List<String>
    )
}

fun HotelDto.About.toDomain(): Hotel.About = Hotel.About(description, peculiarities)
fun HotelDto.toDomain(): Hotel = Hotel(
    id,
    name,
    address,
    minimalPrice,
    priceForIt,
    rating,
    ratingName,
    imageUrls,
    aboutTheHotel.toDomain()
)



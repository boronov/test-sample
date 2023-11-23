package ru.appsmile.test.hotel.data.remote.dtos

import com.squareup.moshi.Json
import ru.appsmile.test.hotel.domain.model.Room

data class RoomsDto(
    @field:Json(name = "rooms") val rooms: List<RoomDto>
)

data class RoomDto(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "price") val price: Double,
    @field:Json(name = "price_per") val pricePer: String,
    @field:Json(name = "peculiarities") val peculiarities: List<String>,
    @field:Json(name = "image_urls") val imageUrls: List<String>,
)

fun RoomDto.toDomain(): Room = Room(
    id, name, price, pricePer, peculiarities, imageUrls
)
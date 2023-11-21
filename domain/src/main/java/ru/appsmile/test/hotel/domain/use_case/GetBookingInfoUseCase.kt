package ru.appsmile.test.hotel.domain.use_case

import ru.appsmile.test.hotel.domain.repository.HotelRepository
import javax.inject.Inject

class GetBookingInfoUseCase @Inject constructor(
    private val repository: HotelRepository
) {
    suspend operator fun invoke() = repository.getBookingInfo()
}
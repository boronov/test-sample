package ru.appsmile.test.hotel.presentation.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.appsmile.test.hotel.domain.Resource
import ru.appsmile.test.hotel.domain.model.Hotel
import ru.appsmile.test.hotel.domain.model.Room
import ru.appsmile.test.hotel.domain.use_case.GetHotelRoomsUseCase
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val getHotelRoomsUseCase: GetHotelRoomsUseCase
) : ViewModel() {
    private val _rooms = MutableStateFlow<Resource<List<Room>>>(Resource.Loading())
    val rooms = _rooms.asStateFlow()

    init {
        viewModelScope.launch {
            getHotelRoomsUseCase().collect {
                _rooms.value = it
            }
        }
    }
}
package ru.appsmile.test.hotel.presentation.hotel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.appsmile.test.hotel.domain.Resource
import ru.appsmile.test.hotel.domain.model.Hotel
import ru.appsmile.test.hotel.domain.use_case.GetHotelUseCase
import javax.inject.Inject

@HiltViewModel
class HotelViewModel @Inject constructor(
    private val getHotelUseCase: GetHotelUseCase
) : ViewModel() {

    private val _hotel = MutableStateFlow<Resource<Hotel>>(Resource.Loading())
    val hotel = _hotel.asStateFlow()

    init {
        viewModelScope.launch {
            getHotelUseCase().collect {
                _hotel.value = it
            }
        }
    }
}
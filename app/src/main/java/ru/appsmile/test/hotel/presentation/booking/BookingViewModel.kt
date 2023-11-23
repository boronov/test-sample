package ru.appsmile.test.hotel.presentation.booking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.appsmile.test.hotel.domain.Resource
import ru.appsmile.test.hotel.domain.model.BookingInfo
import ru.appsmile.test.hotel.domain.use_case.GetBookingInfoUseCase
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(
    private val getBookingInfoUseCase: GetBookingInfoUseCase
) : ViewModel() {

    private val _bookingInfo = MutableStateFlow<Resource<BookingInfo>>(Resource.Loading())
    val bookingInfo = _bookingInfo.asStateFlow()

    init {
        viewModelScope.launch {
            getBookingInfoUseCase().collect {
                _bookingInfo.value = it
            }
        }
    }
}
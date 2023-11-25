package ru.appsmile.test.hotel.domain

sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()

    class Error<T>(val error: String? = null) : Resource<T>()

    class Loading<T> : Resource<T>()
}
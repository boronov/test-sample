package ru.appsmile.test.hotel.domain

interface DataMapper<Data : Any, Domain : Any> {
    fun mapToDomain(data: Data): Domain
}
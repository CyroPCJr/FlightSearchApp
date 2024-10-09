package com.example.flightsearchapp.data

import kotlinx.coroutines.flow.Flow

interface AirportRepository {

    suspend fun getAllAirports(): Flow<List<Airport>>

    suspend fun getAirportsName(name: String): Flow<Airport>

    suspend fun getAirportsIata(iata: String): Flow<Airport>
}
package com.example.flightsearchapp.data

import kotlinx.coroutines.flow.Flow

class OfflineAirportRepository(private val airportDao: AirportDao) : AirportRepository {

    override suspend fun getAllAirports(): Flow<List<Airport>> = airportDao.getAllAirports()

    override suspend fun getAirportsName(name: String): Flow<Airport> =
        airportDao.getAirportsName(name)

    override suspend fun getAirportsIata(iata: String): Flow<Airport> =
        airportDao.getAirportsIata(iata)
}
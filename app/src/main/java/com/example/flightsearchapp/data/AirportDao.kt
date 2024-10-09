package com.example.flightsearchapp.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {

    @Query("SELECT * FROM airport ORDER BY name")
    fun getAllAirports(): Flow<List<Airport>>

    @Query("SELECT * FROM airport WHERE name = :name ORDER BY name")
    fun getAirportsName(name: String): Flow<Airport>

    @Query("SELECT * FROM airport WHERE iata_code = :iata ORDER BY iata_code")
    fun getAirportsIata(iata: String): Flow<Airport>
}
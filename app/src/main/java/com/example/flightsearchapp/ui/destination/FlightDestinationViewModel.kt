package com.example.flightsearchapp.ui.destination

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsearchapp.data.Airport
import com.example.flightsearchapp.data.AirportRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class FlightDestinationViewModel(
    savedStateHandle: SavedStateHandle,
    private val airportRepository: AirportRepository,
) : ViewModel() {

    private val airportId: Int = checkNotNull(savedStateHandle[FlightDestinationRoute.AIRPORT_ID])

    val airport: StateFlow<Airport?> = airportRepository.getAirportById(airportId).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = Airport(id = 0, name = "", iata = "", passengers = 0)
    )

    val airportList: StateFlow<List<Airport>> =
        airportRepository.getAirportsStreamById(airportId).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

}

data class FlightDestinationUiState(
    var airport: Airport? = null,
    var airportList: List<Airport> = emptyList(),
)
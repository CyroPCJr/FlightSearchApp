package com.example.flightsearchapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsearchapp.data.Airport
import com.example.flightsearchapp.data.AirportRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeScreenViewModel(private val airportRepository: AirportRepository) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    var homeUiState: StateFlow<HomeUiState> = airportRepository.getAllAirports()
        .map { HomeUiState(airport = it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUiState()
        )

    fun onAirportValueChange(search: String) {
        homeUiState =
            airportRepository.getAirportsByIata(search).map {
                HomeUiState(it)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )
    }
}

data class HomeUiState(
    var airport: List<Airport> = listOf(),
)
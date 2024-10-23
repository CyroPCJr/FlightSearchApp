package com.example.flightsearchapp.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearchapp.FlightSearchApplication
import com.example.flightsearchapp.ui.destination.FlightDestinationViewModel
import com.example.flightsearchapp.ui.home.HomeScreenViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeScreenViewModel(flightSearchApplication().container.airportRepository)
        }

        initializer {
            FlightDestinationViewModel(
                this.createSavedStateHandle(),
                flightSearchApplication().container.airportRepository
            )
        }
    }
}

fun CreationExtras.flightSearchApplication(): FlightSearchApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as FlightSearchApplication)
package com.example.flightsearchapp.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsearchapp.data.Airport
import com.example.flightsearchapp.data.AirportRepository
import com.example.flightsearchapp.data.FavoriteRepository
import com.example.flightsearchapp.model.FavoriteFlights
import com.example.flightsearchapp.ui.theme.CustomKorma
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val airportRepository: AirportRepository,
    private val favoriteRepository: FavoriteRepository,
) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    var uiState by mutableStateOf(HomeUiState())
        private set

    var favoriteUiState by mutableStateOf(FavoriteListUiState())
        private set

//    var homeUiState: StateFlow<HomeUiState> = airportRepository.getAllAirports()
//        .map { HomeUiState(airport = it) }
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
//            initialValue = HomeUiState()
//        )
//
//    fun onAirportValueChange(search: String) {
//        homeUiState =
//            airportRepository.getAirportsByIata(search).map {
//                HomeUiState(it,search)
//            }.stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
//                initialValue = HomeUiState()
//            )
//    }

    fun onAirportValueChange(search: String) {
        viewModelScope.launch {
            airportRepository.getAirportsByIata(search)
                .map { airports ->
                    HomeUiState(airport = airports, searchText = search)
                }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                    initialValue = HomeUiState()
                ).collect { newState ->
                    uiState = newState
                }
        }
    }

    fun loadFavoriteList() {
        viewModelScope.launch {
            favoriteRepository.getAllFavorites().map {
                FavoriteListUiState(airportsFavorite = it)
            }.collect { newState ->
                favoriteUiState = newState
            }
        }
    }


}

data class HomeUiState(
    var airport: List<Airport> = listOf(),
    val searchText: String = "",
)

data class FavoriteListUiState(
    val airportsFavorite: List<FavoriteFlights> = emptyList(),
    val buttonColor: Color = CustomKorma,
)
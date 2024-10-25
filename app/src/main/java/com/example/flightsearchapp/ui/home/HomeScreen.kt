package com.example.flightsearchapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearchapp.FlightSearchTopAppBar
import com.example.flightsearchapp.R
import com.example.flightsearchapp.data.Airport
import com.example.flightsearchapp.ui.AppViewModelProvider
import com.example.flightsearchapp.ui.components.FlightDestinationDetails
import com.example.flightsearchapp.ui.components.FlightSearchDisplay
import com.example.flightsearchapp.ui.navigation.NavigationDestination
import com.example.flightsearchapp.ui.theme.FlightSearchAppTheme

object HomeScreenDestination : NavigationDestination {
    override val route: String = "home"
    override val titleRes: Int = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToSelectFlight: (Int) -> Unit,
    viewModel: HomeScreenViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            FlightSearchTopAppBar(
                title = R.string.top_bar_app_name,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        HomeBody(
            viewModel = viewModel,
            navigateToSelectFlight = navigateToSelectFlight,
            onAirportValueChange = viewModel::onAirportValueChange,
            contentPaddingValues = innerPadding
        )
    }
}

@Composable
private fun HomeBody(
    viewModel: HomeScreenViewModel,
    navigateToSelectFlight: (Int) -> Unit,
    onAirportValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    contentPaddingValues: PaddingValues = PaddingValues(0.dp),
) {
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
                onAirportValueChange(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPaddingValues),
            placeholder = { Text(stringResource(R.string.textfield_placeholder)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = null
                )
            },
            shape = RoundedCornerShape(25.dp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        FlightList(viewModel.uiState.airport, viewModel, navigateToSelectFlight)
    }
}

@Composable
private fun FlightList(
    airportList: List<Airport>,
    viewModel: HomeScreenViewModel,
    navigateToSelectFlight: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (airportList.isNotEmpty()) {
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(airportList, key = { item -> item.id }) { airport ->
                FlightSearchDisplay(airport, navigateToSelectFlight)
            }
        }
    } else {
        viewModel.loadFavoriteList()

        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                viewModel.favoriteUiState.airportsFavorite,
                key = { item -> item.id }) { airportflights ->
                val airportFrom = Airport(
                    id = 0,
                    iata = airportflights.departureIata,
                    name = airportflights.departureName,
                    passengers = 0
                )
                val airportTo = Airport(
                    id = 0,
                    iata = airportflights.destinationIata,
                    name = airportflights.destinationName,
                    passengers = 0
                )
                FlightDestinationDetails(airportFrom, airportTo, onSaveFavorite = { })
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    FlightSearchAppTheme {
        val airportList = listOf(
            Airport(
                id = 2,
                name = "Sheremetyevo - A.S. Pushkin International Airport",
                iata = "SVO",
                passengers = 1234
            ),
            Airport(
                id = 3,
                name = "Munich International Airport",
                iata = "MUC",
                passengers = 1234
            ),
            Airport(
                id = 5,
                name = "Düsseldorf International Airport",
                iata = "DUS",
                passengers = 1234
            ),
            Airport(
                id = 6,
                name = "Athens International Airport",
                iata = "ATH",
                passengers = 1234
            ),
            Airport(
                id = 7,
                name = "Lyon-Saint Exupéry Airport",
                iata = "LYS",
                passengers = 1234
            ),
            Airport(
                id = 8,
                name = "Leonardo da Vinci International Airport",
                iata = "FCO",
                passengers = 1234
            ),
            Airport(
                id = 9,
                name = "Vienna International Airport",
                iata = "VIE",
                passengers = 1234
            ),
            Airport(
                id = 10,
                name = "Keflavík International Airport",
                iata = "KEF",
                passengers = 1234
            )
        )
        val viewModel: HomeScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)

        HomeBody(viewModel = viewModel,
            onAirportValueChange = {},
            navigateToSelectFlight = {})
    }
}
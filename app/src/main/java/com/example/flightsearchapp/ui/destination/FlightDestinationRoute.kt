@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.flightsearchapp.ui.destination

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearchapp.FlightSearchTopAppBar
import com.example.flightsearchapp.R
import com.example.flightsearchapp.data.Airport
import com.example.flightsearchapp.ui.AppViewModelProvider
import com.example.flightsearchapp.ui.components.FlightSearchDisplay
import com.example.flightsearchapp.ui.navigation.NavigationDestination
import com.example.flightsearchapp.ui.theme.FlightSearchAppTheme

object FlightDestinationRoute : NavigationDestination {
    override val route: String = "flightDestination"
    override val titleRes: Int = R.string.flight_destination_screen
    const val AIRPORT_ID = "airportId"
    val routeWithArgs = "$route/{$AIRPORT_ID}"
}

@Composable
fun FlightListDestination(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    flightDestinationViewModel: FlightDestinationViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val airport by flightDestinationViewModel.airport.collectAsState()
    val airportList by flightDestinationViewModel.airportList.collectAsState()

    Scaffold(topBar = {
        FlightSearchTopAppBar(
            title = R.string.flight_destination_screen,
            canNavigateBack = true,
            navigateUp = navigateBack
        )
    })
    { innerPadding ->

        LazyColumn {
            item {
                Text(
                    text = stringResource(
                        R.string.subtitle_favorite_routes,
                        airport!!.iata
                    ),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            items(airportList, key = { airport -> airport.id }) {
                FlightDestinationDetails(
                    airportFrom = airport!!,
                    airportTo = it,
                    onSaveFavorite = { },
                    modifier
                )
            }

        }
    }
}

@Composable
private fun FlightDestinationDetails(
    airportFrom: Airport,
    airportTo: Airport,
    onSaveFavorite: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEEF1F5)),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    FlightDetailSubTitle(R.string.flight_detail_departure)
                    FlightSearchDisplay(airportFrom)

                    Spacer(modifier = Modifier.height(8.dp))

                    FlightDetailSubTitle(R.string.flight_detail_arrive)
                    FlightSearchDisplay(airportTo)
                }

                IconButton(onClick = onSaveFavorite) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = stringResource(R.string.icon_save_favorite),
                        tint = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
private fun FlightDetailSubTitle(@StringRes text: Int) {
    Text(
        text = stringResource(text),
        fontWeight = FontWeight.Bold,
        color = Color.Gray,
        fontSize = 12.sp
    )
}

@Preview(showBackground = true)
@Composable
fun FlightDestinationDetailsPreview() {
    FlightSearchAppTheme {
        val homeUiState = FlightDestinationUiState(
            airportList =
            listOf(
                Airport(id = 1, name = "A S Ff", iata = "ASD", passengers = 1234),
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
            ),
            airport = Airport(
                id = 2,
                name = "Sheremetyevo - A.S. Pushkin International Airport",
                iata = "SVO",
                passengers = 1234
            )
        )

        FlightDestinationDetails(homeUiState.airport!!, homeUiState.airportList[2], {})
    }
}
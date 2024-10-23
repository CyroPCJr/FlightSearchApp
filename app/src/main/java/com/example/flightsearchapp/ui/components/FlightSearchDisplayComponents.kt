package com.example.flightsearchapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.flightsearchapp.data.Airport

@Composable
fun FlightSearchDisplay(
    airportDeparture: Airport,
    onClickAirport: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClickAirport(airportDeparture.id) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = airportDeparture.iata,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(text = airportDeparture.name, style = MaterialTheme.typography.bodyMedium)
    }
}
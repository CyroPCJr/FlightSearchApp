package com.example.flightsearchapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.flightsearchapp.ui.homescreen.HomeScreen

@Composable
fun FlightSearchApp() {
    Scaffold(topBar = { FlightSearchTopAppBar() }) { innerPadding ->
        HomeScreen(modifier = Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FlightSearchTopAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = { stringResource(R.string.app_name) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color(0xFF1565C0),
            titleContentColor = Color.White
        ),
        modifier = modifier
    )
}
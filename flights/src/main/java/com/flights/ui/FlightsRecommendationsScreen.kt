package com.flights.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.flights.ui.theme.FlightsTheme

@Composable
fun FlightsRecommendationsScreen(
    airportSelected: String,
    onBackHandler: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackHandler()
    }
    Column(
        modifier = modifier
    ) {
        Text(text = "Flights recommendations for $airportSelected")
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    FlightsTheme {
        FlightsRecommendationsScreen(airportSelected = "EZE", onBackHandler = {})
    }
}
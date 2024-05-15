package com.flights.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.flights.R
import com.flights.data.RecommendationFlight

@Composable
fun FlightsRecommendationsScreen(
    uiState: UiState,
    addFlightToFavoritesEvent: (RecommendationFlight) -> Unit,
    removeFlightFromFavoritesEvent: (RecommendationFlight) -> Unit,
    onBackHandler: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BackHandler {
        onBackHandler()
    }
    Column(
        modifier = modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_medium))
    ) {
        Text(
            text = stringResource(R.string.recommended_flights_from, uiState.currentAirportCode),
            style = MaterialTheme.typography.titleLarge,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_medium))
        )
        Flights(
            flights = uiState.recommendedFlights,
            addFlightToFavoritesEvent = addFlightToFavoritesEvent,
            removeFlightFromFavoritesEvent = removeFlightFromFavoritesEvent
        )
    }

}
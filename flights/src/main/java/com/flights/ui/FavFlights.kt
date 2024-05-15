package com.flights.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.flights.R
import com.flights.data.RecommendationFlight

@Composable
fun Flights(
    flights: List<RecommendationFlight>,
    addFlightToFavoritesEvent: (RecommendationFlight) -> Unit,
    removeFlightFromFavoritesEvent: (RecommendationFlight) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
        modifier = modifier
    ) {
        items(flights) {
            FlightItem(
                flight = it,
                addFlightToFavoritesEvent = addFlightToFavoritesEvent,
                removeFlightFromFavoritesEvent = removeFlightFromFavoritesEvent
            )
        }
    }
}

@Composable
private fun FlightItem(
    flight: RecommendationFlight,
    addFlightToFavoritesEvent: (RecommendationFlight) -> Unit,
    removeFlightFromFavoritesEvent: (RecommendationFlight) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        shape = RoundedCornerShape(
            topStart = dimensionResource(id = R.dimen.padding_medium),
            topEnd = dimensionResource(id = R.dimen.padding_medium),
            bottomStart = dimensionResource(id = R.dimen.no_padding),
            bottomEnd = dimensionResource(id = R.dimen.no_padding)
        ),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.depart),
                    fontStyle = FontStyle.Italic
                )
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = modifier
                ) {
                    Text(
                        text = flight.departureIataCode,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small)))
                    Text(
                        text = flight.departureAirportName,
                        fontWeight = FontWeight.Light
                    )
                }
                Spacer(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_small)))
                Text(
                    text = stringResource(R.string.arrive),
                    fontStyle = FontStyle.Italic
                )
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = modifier
                ) {
                    Text(
                        text = flight.destinationIataCode,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small)))
                    Text(
                        text = flight.destinationAirportName,
                        fontWeight = FontWeight.Light
                    )
                }
            }
            IconButton(onClick = { if(flight.isFavorite) removeFlightFromFavoritesEvent(flight) else addFlightToFavoritesEvent(flight)},
                modifier = Modifier.weight(0.1f)
            ) {
                Icon(imageVector = if(flight.isFavorite) Icons.Outlined.Star else Icons.Outlined.StarBorder, contentDescription = null)
            }
        }
    }
}
package com.flights.ui

import android.content.res.Configuration
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.flights.R
import com.flights.data.Favorite
import com.flights.ui.theme.FlightsTheme

@Composable
fun FavFlights(
    favFlights: List<Favorite>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(favFlights) {
            FavFlightItem(it)
        }
    }
}

@Composable
private fun FavFlightItem(
    favFlight: Favorite? = null,
    modifier: Modifier = Modifier
) {
    var favorite by remember { mutableStateOf(false) }
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
                        text = "EZE",
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small)))
                    Text(
                        text = "Aeropuerto de Ezeiza",
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
                        text = "AER",
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small)))
                    Text(
                        text = "Aeroparque",
                        fontWeight = FontWeight.Light
                    )
                }
            }
            IconButton(onClick = { favorite = !favorite },
                modifier = Modifier.weight(0.1f)
            ) {
                Icon(imageVector = if(favorite) Icons.Outlined.Star else Icons.Outlined.StarBorder, contentDescription = null)
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun FavFlightsPreview() {
    FlightsTheme {
        FavFlightItem()
    }
}
package com.flights.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.flights.R
import com.flights.data.Airport
import com.flights.ui.theme.FlightsTheme

@Composable
fun FlightsApp() {
    Scaffold(
        topBar = {
            FlightsTopBar()
        }
    ) { contentPadding ->
        val viewModel: FlightsViewModel = viewModel(factory = FlightsViewModel.factory)
        val uiState by viewModel.uiState.collectAsState()
        HomeContent(
            viewModel = viewModel,
            uiState = uiState,
            modifier = Modifier.padding(contentPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FlightsTopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.top_bar_title),
                style = MaterialTheme.typography.titleLarge)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background
        ))
}

@Composable
private fun HomeContent(
    viewModel: FlightsViewModel,
    uiState: FlightUiState,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(
            top = dimensionResource(id = R.dimen.padding_large),
            end = dimensionResource(id = R.dimen.padding_large),
            start = dimensionResource(id = R.dimen.padding_large)
        )
    ) {
        AirportsSearchBar(
            uiState = uiState,
            changeSearchStrategyEvent = { viewModel.changeSearchStrategy(it) },
            updateSearchEvent = { viewModel.updateCurrentSearch(it) },
            searchAirport = { viewModel.searchAirports(it) }
        )
        Spacer(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_medium)))
        AirportsList(airports = viewModel.uiState.collectAsState().value.airports)
    }
}

@Composable
private fun AirportsSearchBar(
    uiState: FlightUiState,
    changeSearchStrategyEvent: (Boolean) -> Unit,
    updateSearchEvent: (FlightUiState) -> Unit,
    searchAirport: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    TextField(
        value = uiState.currentSearch,
        onValueChange = {
            searchAirport(it)
            if (uiState.isSearchingByIATACode) {
                if (it.length <= 3) {
                    updateSearchEvent(uiState.copy(currentSearch = it))
                }
            } else {
                updateSearchEvent(uiState.copy(currentSearch = it))
            }
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
        },
        trailingIcon = {
            Checkbox(
                checked = uiState.isSearchingByIATACode,
                onCheckedChange = {
                    changeSearchStrategyEvent(it)
                }
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
            focusedIndicatorColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier
            .fillMaxWidth(),
        placeholder = {
            Text(
                text = if(uiState.isSearchingByIATACode) {
                    stringResource(R.string.search_airport_by_iata)
                } else {
                    stringResource(R.string.search_airport_by_name)
                }
            )
        },
        shape = RoundedCornerShape(
            topStart = dimensionResource(id = R.dimen.padding_medium),
            topEnd = dimensionResource(id = R.dimen.padding_medium),
            bottomStart = dimensionResource(id = R.dimen.no_padding),
            bottomEnd = dimensionResource(id = R.dimen.no_padding)
        ),
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                focusManager.clearFocus()
                keyboardController?.hide()
            }
        )
    )
}

@Composable
private fun AirportsList(
    airports: List<Airport>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_medium)))
        LazyColumn {
            items(airports, key = { airport -> airport.id }) { airport ->
                FlightItem(airport = airport)
            }
        }
    }
}

@Composable
private fun FlightItem(
    airport: Airport,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
    ) {
        Text(
            text = airport.iataCode,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small)))
        Text(text = airport.name)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun PreviewHome() {
    FlightsTheme {
        FlightsApp()
    }
}
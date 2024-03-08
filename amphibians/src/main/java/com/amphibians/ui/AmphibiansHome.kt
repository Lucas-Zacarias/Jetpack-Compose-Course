package com.amphibians.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.amphibians.R
import com.amphibians.model.Amphibian
import com.amphibians.ui.theme.AmphibiansTheme

@Composable
fun AmphibiansHome(
    uiState: AmphibiansUiState,
    retryEvent: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    when (uiState) {
        is AmphibiansUiState.Loading -> LoadingScreen(modifier.fillMaxSize())
        is AmphibiansUiState.Success ->
            AmphibiansList(
                amphibians = uiState.amphibians,
                modifier = modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_medium)),
                contentPadding = contentPadding
            )

        is AmphibiansUiState.Error ->
            ErrorScreen(
                retryEvent = retryEvent,
                modifier = modifier.fillMaxSize()
            )
    }
}

@Composable
private fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading_image),
        alignment = Alignment.Center,
        modifier = modifier.size(dimensionResource(id = R.dimen.image_size_medium))
    )
}

@Composable
private fun AmphibiansList(
    amphibians: List<Amphibian>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        verticalArrangement = Arrangement
            .spacedBy(dimensionResource(id = R.dimen.padding_large)),
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        items(
            items = amphibians,
            key = { amphibian -> amphibian.name }
        ) { amphibian ->
            AmphibianItem(
                amphibian = amphibian
            )
        }
    }
}

@Composable
private fun AmphibianItem(
    amphibian: Amphibian,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Column {
            AmphibianName(amphibian)
            AmphibianImage(amphibian)
            AmphibianDescription(amphibian)
        }
    }
}

@Composable
private fun AmphibianName(amphibian: Amphibian) {
    Row(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
    ) {
        Text(
            text = amphibian.name,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_small)))
        Text(
            text = "(${amphibian.type})",
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
private fun AmphibianImage(amphibian: Amphibian) {
    AsyncImage(
        model =
        ImageRequest
            .Builder(context = LocalContext.current)
            .data(amphibian.imgSrc)
            .crossfade(true)
            .build(),
        contentDescription = amphibian.name,
        contentScale = ContentScale.Crop,
        error = painterResource(id = R.drawable.ic_broken_image),
        placeholder = painterResource(id = R.drawable.loading_img),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun AmphibianDescription(amphibian: Amphibian) {
    Text(
        text = amphibian.description,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
    )
}

@Composable
private fun ErrorScreen(
    retryEvent: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = stringResource(R.string.connection_error),
            modifier = Modifier.size(dimensionResource(id = R.dimen.image_size_medium))
        )
        Text(
            text = stringResource(id = R.string.connection_error),
            style = MaterialTheme.typography.titleLarge
        )
        Button(
            onClick = retryEvent,
            shape = MaterialTheme.shapes.small
        ) {
            Text(
                text = stringResource(id = R.string.retry),
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewErrorScreen() {
    AmphibiansTheme {
        ErrorScreen(retryEvent = {})
    }
}
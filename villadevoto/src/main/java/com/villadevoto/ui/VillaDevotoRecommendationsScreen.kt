package com.villadevoto.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.villadevoto.R
import com.villadevoto.model.Place
import com.villadevoto.ui.util.VillaDevotoContentType

@Composable
fun VillaDevotoRecommendationsScreen(
    uiState: VillaDevotoAppUiState,
    contentType: VillaDevotoContentType,
    modifier: Modifier = Modifier,
    recommendationEvent: (Place) -> Unit
) {
    if (contentType == VillaDevotoContentType.LIST_ONLY) {
        RecommendationsList(
            recommendations = uiState.placesList,
            currentCategoryIconResourceId = uiState.currentCategory.icon,
            recommendationEvent = recommendationEvent,
            modifier = modifier
        )
    } else {
        RecommendationsExpandedScreen(
            uiState = uiState,
            recommendationEvent = recommendationEvent,
            modifier = modifier
        )
    }
}

@Composable
fun RecommendationsList(
    recommendations: List<Place>,
    currentCategoryIconResourceId: Int,
    recommendationEvent: (Place) -> Unit,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        items(recommendations) { place ->
            RecommendationItem(
                recommendationEvent = recommendationEvent,
                place = place,
                currentCategoryIconResourceId = currentCategoryIconResourceId,
                selected = false
            )
        }
    }
}

@Composable
fun RecommendationItem(
    recommendationEvent: (Place) -> Unit,
    place: Place,
    currentCategoryIconResourceId: Int,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        shape =
        RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        modifier = modifier.padding(vertical = dimensionResource(id = R.dimen.padding_medium))
            .clickable { recommendationEvent(place) },
        colors = CardDefaults.cardColors(
            containerColor = if(selected)
                MaterialTheme.colorScheme.tertiaryContainer
            else
                MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            Image(
                painter = painterResource(id = currentCategoryIconResourceId),
                contentDescription = stringResource(
                    id = place.titleResourceId
                ),
                modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size_medium))
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_medium)))
            Text(text = stringResource(id = place.titleResourceId))
        }
    }
}

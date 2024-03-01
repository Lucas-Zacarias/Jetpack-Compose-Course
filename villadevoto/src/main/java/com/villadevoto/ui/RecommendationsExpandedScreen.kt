package com.villadevoto.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.villadevoto.R
import com.villadevoto.model.Place

@Composable
fun RecommendationsExpandedScreen(
    uiState: VillaDevotoAppUiState,
    recommendationEvent: (Place) -> Unit,
    modifier: Modifier
) {
    Row(
        modifier = modifier
    ) {
        RecommendationsListWithRecommendationSelected(
            recommendations = uiState.placesList,
            currentPlace = uiState.currentPlace,
            currentCategoryIconResourceId = uiState.currentCategory.icon,
            recommendationEvent = recommendationEvent,
            modifier = Modifier.weight(1f)
        )
        VillaDevotoRecommendedPlaceScreen(
            place = uiState.currentPlace,
            modifier = Modifier
                .weight(1f)
                .padding(top = dimensionResource(id = R.dimen.padding_medium))
        )
    }
}

@Composable
fun RecommendationsListWithRecommendationSelected(
    recommendations: List<Place>,
    currentPlace: Place,
    currentCategoryIconResourceId: Int,
    recommendationEvent: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        items(recommendations) { recommendation ->
            RecommendationItem(
                recommendationEvent = recommendationEvent,
                place = recommendation,
                currentCategoryIconResourceId = currentCategoryIconResourceId,
                selected = recommendation == currentPlace
            )
        }
    }
}

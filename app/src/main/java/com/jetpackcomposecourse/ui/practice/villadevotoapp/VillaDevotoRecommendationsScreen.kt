package com.jetpackcomposecourse.ui.practice.villadevotoapp

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.jetpackcomposecourse.R
import com.jetpackcomposecourse.model.Place

@Composable
fun VillaDevotoRecommendationsScreen(
    recommendations: List<Place>,
    currentCategoryIconResourceId: Int,
    recommendationEvent: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    RecommendationsList(recommendations, currentCategoryIconResourceId, recommendationEvent, modifier)
}

@Composable
private fun RecommendationsList(
    recommendations: List<Place>,
    currentCategoryIconResourceId: Int,
    recommendationEvent: (Place) -> Unit,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        items(recommendations) {
            Card(
                shape =
                RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
                modifier = Modifier
                    .padding(vertical = dimensionResource(id = R.dimen.padding_medium))
                    .clickable { recommendationEvent(it) }
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
                            id = it.titleResourceId
                        ),
                        modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size_medium))
                    )
                    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_medium)))
                    Text(text = stringResource(id = it.titleResourceId))
                }
            }
        }
    }
}

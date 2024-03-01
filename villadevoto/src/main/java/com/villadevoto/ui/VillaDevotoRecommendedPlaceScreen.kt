package com.jetpackcomposecourse.ui.practice.villadevotoapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.jetpackcomposecourse.R
import com.villadevoto.model.Place

@Composable
fun VillaDevotoRecommendedPlaceScreen(
    place: com.villadevoto.model.Place,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
                .verticalScroll(rememberScrollState())
        ) {
            RecommendedPlaceImage(
                imageResourceId = place.imageResourceId,
                titleResourceId = place.titleResourceId
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
            RecommededPlaceTitle(
                titleResourceId = place.titleResourceId
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
            RecommendedPlaceAddress(
                addressResourceId = place.addressResourceId
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
            RecommendedPlaceDescription(
                placeDescriptionId = place.descriptionResourceId
            )
        }
    }
}

@Composable
private fun RecommendedPlaceDescription(
    placeDescriptionId: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = placeDescriptionId),
        modifier = modifier
    )
}

@Composable
private fun RecommendedPlaceAddress(
    addressResourceId: Int,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        modifier = modifier
    ) {
        Icon(imageVector = Icons.Filled.Place, contentDescription = null)
        Text(
            text = stringResource(id = addressResourceId),
            fontStyle = FontStyle.Italic
        )
    }
}

@Composable
private fun RecommededPlaceTitle(
    titleResourceId: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = titleResourceId),
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Composable
private fun RecommendedPlaceImage(
    imageResourceId: Int,
    titleResourceId: Int,
    modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = imageResourceId),
        contentDescription = stringResource(id = titleResourceId),
        modifier = modifier.clip(RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)))
    )
}
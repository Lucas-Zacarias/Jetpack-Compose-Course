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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.jetpackcomposecourse.R
import com.jetpackcomposecourse.data.Categories
import com.jetpackcomposecourse.model.Place
import com.jetpackcomposecourse.ui.practice.villadevotoapp.util.VillaDevotoContentType

@Composable
fun VillaDevotoHomeScreen(
    categories: List<Categories>,
    categoryEvent: (Categories) -> Unit,
    contentType: VillaDevotoContentType,
    recommendationEvent: (Place) -> Unit,
    uiState: VillaDevotoAppUiState,
    modifier: Modifier = Modifier
) {
    if(contentType == VillaDevotoContentType.LIST_ONLY) {
        CategoriesList(
            categories = categories,
            categoryEvent = categoryEvent,
            modifier = modifier
        )
    } else {
        VillaDevotoHomeScreenExpanded(
            categories = categories,
            categoryEvent = categoryEvent,
            recommendations = uiState.placesList,
            currentCategoryIconResourceId = uiState.currentCategory.icon,
            recommendationEvent = recommendationEvent,
            categorySelected = uiState.currentCategory,
            modifier = modifier
        )
    }
}

@Composable
fun CategoriesList(
    categories: List<Categories>,
    categoryEvent: (Categories) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        items(categories) { category ->
            CategoryItem(categoryEvent, category, false)
        }
    }
}

@Composable
fun CategoryItem(
    categoryEvent: (Categories) -> Unit,
    category: Categories,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        shape =
        RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        modifier = modifier
            .padding(vertical = dimensionResource(id = R.dimen.padding_medium))
            .clickable { categoryEvent(category) },
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
                painter = painterResource(id = category.icon),
                contentDescription = stringResource(
                    id = category.title
                ),
                modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size_medium))
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_medium)))
            Text(text = stringResource(id = category.title))
        }
    }
}
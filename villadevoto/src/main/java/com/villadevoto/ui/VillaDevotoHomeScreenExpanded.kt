package com.jetpackcomposecourse.ui.practice.villadevotoapp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.jetpackcomposecourse.R
import com.villadevoto.data.Categories
import com.villadevoto.model.Place

@Composable
fun VillaDevotoHomeScreenExpanded(
    categories: List<com.villadevoto.data.Categories>,
    categoryEvent: (com.villadevoto.data.Categories) -> Unit,
    recommendations: List<com.villadevoto.model.Place>,
    currentCategoryIconResourceId: Int,
    recommendationEvent: (com.villadevoto.model.Place) -> Unit,
    modifier: Modifier = Modifier,
    categorySelected: com.villadevoto.data.Categories
) {
    Row(
        modifier = modifier
    ) {
        CategoriesListWithSelectedCategory(
            categories = categories,
            categoryEvent = categoryEvent,
            categorySelected = categorySelected,
            modifier = Modifier.weight(1f)
        )
        RecommendationsList(
            recommendations = recommendations,
            currentCategoryIconResourceId = currentCategoryIconResourceId,
            recommendationEvent = recommendationEvent,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun CategoriesListWithSelectedCategory(
    categories: List<com.villadevoto.data.Categories>,
    categoryEvent: (com.villadevoto.data.Categories) -> Unit,
    modifier: Modifier,
    categorySelected: com.villadevoto.data.Categories
) {
    LazyColumn(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        items(categories) { category ->
            CategoryItem(categoryEvent, category, category == categorySelected)
        }
    }
}

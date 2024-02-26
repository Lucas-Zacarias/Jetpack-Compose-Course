package com.jetpackcomposecourse.ui.practice.villadevotoapp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.jetpackcomposecourse.R
import com.jetpackcomposecourse.data.Categories
import com.jetpackcomposecourse.model.Place

@Composable
fun VillaDevotoHomeScreenExpanded(
    categories: List<Categories>,
    categoryEvent: (Categories) -> Unit,
    recommendations: List<Place>,
    currentCategoryIconResourceId: Int,
    recommendationEvent: (Place) -> Unit,
    modifier: Modifier = Modifier,
    categorySelected: Categories
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
    categories: List<Categories>,
    categoryEvent: (Categories) -> Unit,
    modifier: Modifier,
    categorySelected: Categories
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

package com.jetpackcomposecourse.ui.practice.villadevotoapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import com.jetpackcomposecourse.R
import com.jetpackcomposecourse.data.Categories
import com.jetpackcomposecourse.ui.practice.villadevotoapp.theme.VillaDevotoTheme

@Composable
fun VillaDevotoHomeScreen(
    categories: List<Categories>,
    categoryEvent: (Categories) -> Unit,
    modifier: Modifier = Modifier
) {
    CategoriesList(
        categories = categories,
        categoryEvent = categoryEvent,
        modifier = modifier
    )
}

@Composable
private fun CategoriesList(
    categories: List<Categories>,
    categoryEvent: (Categories) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        items(categories) {
            Card(
                shape =
                RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
                modifier = Modifier
                    .padding(vertical = dimensionResource(id = R.dimen.padding_medium))
                    .clickable { categoryEvent(it) }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                ) {
                    Image(
                        painter = painterResource(id = it.icon),
                        contentDescription = stringResource(
                            id = it.title
                        ),
                        modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size_medium))
                    )
                    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_medium)))
                    Text(text = stringResource(id = it.title))
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomePreview() {
    VillaDevotoTheme {
        VillaDevotoHomeScreen(
            categories = Categories.entries.toList(),
            modifier = Modifier.fillMaxSize(),
            categoryEvent = {}
        )
    }
}
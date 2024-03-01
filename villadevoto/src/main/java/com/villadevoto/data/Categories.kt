package com.villadevoto.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.villadevoto.R

enum class Categories(
    @StringRes val title: Int,
    @DrawableRes val icon: Int
) {
    COFFEE_SHOPS(
        title = R.string.coffee_shops,
        icon = R.drawable.ic_coffee
    ),
    RESTAURANTS(
        title = R.string.restaurants,
        icon = R.drawable.ic_restaurant
    ),
    KID_FRIENDLY_PLACES(
        title = R.string.kid_friendly_places,
        icon = R.drawable.ic_kids
    ),
    PARKS(
        title = R.string.parks,
        icon = R.drawable.ic_park
    ),
    SHOPPING_CENTERS(
        title = R.string.shopping_centers,
        icon = R.drawable.ic_shopping
    )
}
package com.villadevoto.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.villadevoto.data.Categories

data class Place(
    val id: Int,
    @StringRes val titleResourceId: Int,
    @StringRes val descriptionResourceId: Int,
    @DrawableRes val imageResourceId: Int,
    val category: Categories,
    @StringRes val addressResourceId: Int
)

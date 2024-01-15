package com.jetpackcomposecourse.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Stretching(
    @StringRes val nameRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val imageRes: Int
)

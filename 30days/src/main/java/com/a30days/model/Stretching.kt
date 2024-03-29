package com.a30days.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Stretching(
    @StringRes val nameRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val imageRes: Int
)

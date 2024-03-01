package com.villadevoto.ui

import androidx.annotation.StringRes
import com.villadevoto.R

enum class Screens(@StringRes val title: Int) {
    CATEGORIES_SCREEN(R.string.app_name),
    RECOMMENDATIONS_SCREEN(R.string.categories),
    RECOMMENDED_PLACE_SCREEN(R.string.recommended_place)
}
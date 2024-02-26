package com.jetpackcomposecourse.ui.practice.villadevotoapp

import androidx.annotation.StringRes
import com.jetpackcomposecourse.R

enum class Screens(@StringRes val title: Int) {
    CATEGORIES_SCREEN(R.string.villa_devoto_app_name),
    RECOMMENDATIONS_SCREEN(R.string.categories),
    RECOMMENDED_PLACE_SCREEN(R.string.recommended_place),
    HOME_SCREEN_EXPANDED(R.string.villa_devoto_app_name),
    RECOMMENDED_PLACE_EXPANDED(R.string.villa_devoto_app_name)
}
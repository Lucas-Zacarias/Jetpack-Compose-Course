package com.jetpackcomposecourse.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val topicName: Int,
    val numberOfCourses: Int,
    @DrawableRes val topicImage: Int
)

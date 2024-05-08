package com.flights.ui

import androidx.annotation.StringRes
import com.flights.R

sealed class Screens(
    val route: String,
    @StringRes val title: Int
) {
    data object Home : Screens(route = "home", title = R.string.app_name)
    data object FlightsRecommendations : Screens(route = "flights_from", title = R.string.flights_from)

    fun withArgs(args: String): String {
        return buildString {
            append(route)
            append("/$args")
        }
    }

}

object TitleUtil{
    fun findTitle(route: String): Screens {
        return when {
            route.startsWith("flights_from/") -> Screens.FlightsRecommendations
            else -> Screens.Home
        }
    }
}
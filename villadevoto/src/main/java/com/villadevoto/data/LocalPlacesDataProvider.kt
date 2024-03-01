package com.jetpackcomposecourse.data

import com.jetpackcomposecourse.R
import com.jetpackcomposecourse.model.Place

object LocalPlacesDataProvider {
    val defaultPlace = getPlacesData()[0]

    fun getPlacesByCategory(category: Categories): List<Place> {
        return getPlacesData().filter { place ->
            place.category == category
        }
    }

    fun getPlacesData(): List<Place> {
        return listOf(
            Place(
                id = 1,
                titleResourceId = R.string.betular_patisserie,
                descriptionResourceId = R.string.betular_patisserie_description,
                imageResourceId = R.drawable.coffee_store,
                category = Categories.COFFEE_SHOPS,
                addressResourceId = R.string.betular_patisserie_address
            ),
            Place(
                id = 2,
                titleResourceId = R.string.la_panera_rosa,
                descriptionResourceId = R.string.la_panera_rosa_description,
                imageResourceId = R.drawable.coffee_store,
                category = Categories.COFFEE_SHOPS,
                addressResourceId = R.string.la_panera_rosa_address
            ),
            Place(
                id = 3,
                titleResourceId = R.string.valentino_caffe,
                descriptionResourceId = R.string.valentino_caffe_description,
                imageResourceId = R.drawable.coffee_store,
                category = Categories.COFFEE_SHOPS,
                addressResourceId = R.string.valentino_caffe_address
            ),
            Place(
                id = 4,
                titleResourceId = R.string.korto_cafe,
                descriptionResourceId = R.string.korto_cafe_description,
                imageResourceId = R.drawable.coffee_store,
                category = Categories.COFFEE_SHOPS,
                addressResourceId = R.string.korto_cafe_address
            ),
            Place(
                id = 5,
                titleResourceId = R.string.burger_54,
                descriptionResourceId = R.string.burger_54_description,
                imageResourceId = R.drawable.restaurant,
                category = Categories.RESTAURANTS,
                addressResourceId = R.string.burger_54_address
            ),
            Place(
                id = 6,
                titleResourceId = R.string.mecha_fuego_porteño,
                descriptionResourceId = R.string.mecha_fuego_porteño_description,
                imageResourceId = R.drawable.restaurant,
                category = Categories.RESTAURANTS,
                addressResourceId = R.string.mecha_fuego_porteño_address
            ),
            Place(
                id = 7,
                titleResourceId = R.string.sunny_pizza_y_friends,
                descriptionResourceId = R.string.sunny_pizza_y_friends_description,
                imageResourceId = R.drawable.restaurant,
                category = Categories.RESTAURANTS,
                addressResourceId = R.string.sunny_pizza_y_friends_address
            ),
            Place(
                id = 8,
                titleResourceId = R.string.polo_gastronomico_chivilcoy,
                descriptionResourceId = R.string.polo_gastronomico_chivilcoy_description,
                imageResourceId = R.drawable.restaurant,
                category = Categories.RESTAURANTS,
                addressResourceId = R.string.polo_gastronomico_chivilcoy_address
            ),
            Place(
                id = 9,
                titleResourceId = R.string.playland_park,
                descriptionResourceId = R.string.playland_park_description,
                imageResourceId = R.drawable.children,
                category = Categories.KID_FRIENDLY_PLACES,
                addressResourceId = R.string.playland_park_address
            ),
            Place(
                id = 10,
                titleResourceId = R.string.cinema_devoto,
                descriptionResourceId = R.string.cinema_devoto_description,
                imageResourceId = R.drawable.children,
                category = Categories.KID_FRIENDLY_PLACES,
                addressResourceId = R.string.cinema_devoto_address
            ),
            Place(
                id = 11,
                titleResourceId = R.string.plaza_arenales,
                descriptionResourceId = R.string.plaza_arenales_description,
                imageResourceId = R.drawable.park,
                category = Categories.PARKS,
                addressResourceId = R.string.plaza_arenales_address
            ),
            Place(
                id = 12,
                titleResourceId = R.string.polideportivo_parque_onega,
                descriptionResourceId = R.string.polideportivo_parque_onega_description,
                imageResourceId = R.drawable.park,
                category = Categories.PARKS,
                addressResourceId = R.string.polideportivo_parque_onega_address
            ),
            Place(
                id = 13,
                titleResourceId = R.string.shopping_devoto,
                descriptionResourceId = R.string.shopping_devoto_description,
                imageResourceId = R.drawable.shopping,
                category = Categories.SHOPPING_CENTERS,
                addressResourceId = R.string.shopping_devoto_address
            ),
            Place(
                id = 14,
                titleResourceId = R.string.gran_galeria_devoto,
                descriptionResourceId = R.string.gran_galeria_devoto_description,
                imageResourceId = R.drawable.shopping,
                category = Categories.SHOPPING_CENTERS,
                addressResourceId = R.string.gran_galeria_devoto_address
            ),
            Place(
                id = 15,
                titleResourceId = R.string.paseo_de_la_plaza,
                descriptionResourceId = R.string.paseo_de_la_plaza_description,
                imageResourceId = R.drawable.shopping,
                category = Categories.SHOPPING_CENTERS,
                addressResourceId = R.string.paseo_de_la_plaza_address
            )
        )
    }
}
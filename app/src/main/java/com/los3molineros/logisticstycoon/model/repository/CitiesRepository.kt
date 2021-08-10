package com.los3molineros.logisticstycoon.model.repository

import com.los3molineros.logisticstycoon.model.data.Cities

class CitiesRepository() {

    val citiesList = listOf<Cities>(
        Cities(0, "Madrid", 4.35533, 8.343422),
        Cities(1, "Barcelona", 5.42423, 5.3434342 )
    )

    fun returnAllCities(): List<Cities> {
        return citiesList
    }

    fun returnCityNameList(): List<String> {
        val cityList = mutableListOf<String>()

        for (cities in citiesList) {
            cityList.add(cities.name)
        }

        return cityList
    }

}
package com.veselovvv.drinks.data.cocktaildetails.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface CocktailDetailsCloudDataSource {
    suspend fun fetchCocktailDetails(cocktailName: String): CocktailDetailsCloud

    class Base(
        private val service: CocktailDetailsService, private val gson: Gson
    ) : CocktailDetailsCloudDataSource {
        private val type = object : TypeToken<DrinksDetailsCloud>() {}.type

        override suspend fun fetchCocktailDetails(cocktailName: String): CocktailDetailsCloud {
            val convertedName = cocktailName.replace(OLD_SEPARATOR, NEW_SEPARATOR).lowercase()
            val drinksDetails: DrinksDetailsCloud =
                gson.fromJson(service.fetchCocktailDetails(convertedName).string(), type)
            val cocktailsDetails = drinksDetails.getCocktailsList()
            cocktailsDetails.forEach {
                if (it.getName() == cocktailName) return it
            }
            return cocktailsDetails[0]
        }

        companion object {
            private const val OLD_SEPARATOR = ' '
            private const val NEW_SEPARATOR = '_'
        }
    }
}
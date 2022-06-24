package com.veselovvv.drinks.data.cocktails.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface CocktailsCloudDataSource {
    suspend fun fetchCocktails(): List<CocktailCloud>

    class Base(
        private val service: CocktailsService, private val gson: Gson
    ) : CocktailsCloudDataSource {
        private val type = object : TypeToken<DrinksCloud>() {}.type

        override suspend fun fetchCocktails(): List<CocktailCloud> {
            val drinks: DrinksCloud = gson.fromJson(service.fetchCocktails().string(), type)
            return drinks.getCocktailsList()
        }
    }
}
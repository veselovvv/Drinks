package com.veselovvv.drinks.data.cocktaildetails.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface CocktailDetailsCloudDataSource {
    suspend fun fetchCocktailDetails(cocktailName: String): CocktailDetailsCloud

    class Base(
        private val service: CocktailDetailsService, private val gson: Gson
    ) : CocktailDetailsCloudDataSource {
        private val type = object : TypeToken<CocktailDetailsCloud>() {}.type

        // TODO change name like Cocktail Name to cocktail_name in UseCase
        override suspend fun fetchCocktailDetails(cocktailName: String): CocktailDetailsCloud =
            gson.fromJson(service.fetchCocktailDetails(cocktailName).string(), type)
    }
}
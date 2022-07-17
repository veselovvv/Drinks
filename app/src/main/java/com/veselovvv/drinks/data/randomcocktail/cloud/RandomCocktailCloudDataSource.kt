package com.veselovvv.drinks.data.randomcocktail.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface RandomCocktailCloudDataSource {
    suspend fun fetchRandomCocktail(): RandomCocktailCloud

    class Base(
        private val service: RandomCocktailService, private val gson: Gson
    ) : RandomCocktailCloudDataSource {
        private val type = object : TypeToken<DrinksRandomCloud>() {}.type

        override suspend fun fetchRandomCocktail(): RandomCocktailCloud {
            val drinksRandom: DrinksRandomCloud =
                gson.fromJson(service.fetchRandomCocktail().string(), type)
            return drinksRandom.getCocktailsList()[0]
        }
    }
}
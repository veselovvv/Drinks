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
            val drinks = mutableListOf<DrinksCloud>()
            for (letter in 'a'..'z') {
                drinks.add(gson.fromJson(service.fetchCocktails(letter.toString()).string(), type))
            }
            val cocktails = mutableListOf<CocktailCloud>()
            drinks.forEach { cocktails += it.getCocktailsList() }
            return cocktails
        }
    }
}
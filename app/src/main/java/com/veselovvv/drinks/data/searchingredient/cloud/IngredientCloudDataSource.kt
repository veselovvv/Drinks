package com.veselovvv.drinks.data.searchingredient.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface IngredientCloudDataSource {
    suspend fun fetchIngredient(name: String): IngredientCloud

    class Base(
        private val service: SearchIngredientService, private val gson: Gson
    ) : IngredientCloudDataSource {
        private val type = object : TypeToken<IngredientsCloud>() {}.type

        override suspend fun fetchIngredient(name: String): IngredientCloud {
            val ingredients: IngredientsCloud = gson.fromJson(service.fetchIngredient(name).string(), type)
            return ingredients.getIngredientsList()[0]
        }
    }
}
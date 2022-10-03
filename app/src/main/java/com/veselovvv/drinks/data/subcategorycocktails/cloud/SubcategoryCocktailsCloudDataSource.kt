package com.veselovvv.drinks.data.subcategorycocktails.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface SubcategoryCocktailsCloudDataSource {
    suspend fun fetchCocktails(
        categoryKey: String, subcategoryName: String
    ): List<SubcategoryCocktailCloud>

    class Base(
        private val service: SubcategoryCocktailsService, private val gson: Gson
    ) : SubcategoryCocktailsCloudDataSource {
        private val type = object : TypeToken<SubcategoryDrinksCloud>() {}.type

        override suspend fun fetchCocktails(
            categoryKey: String, subcategoryName: String
        ): List<SubcategoryCocktailCloud> {
            val drinks = when (categoryKey) {
                "c" -> service.fetchCategoriesSubcategoryCocktails(subcategoryName)
                "g" -> service.fetchGlassSubcategoryCocktails(subcategoryName)
                "i" -> service.fetchIngredientsSubcategoryCocktails(subcategoryName)
                else -> service.fetchAlcoholicSubcategoryCocktails(subcategoryName)
            }

            val drinksCloud: SubcategoryDrinksCloud = gson.fromJson(drinks.string(), type)
            return drinksCloud.getCocktailsList()
        }
    }
}
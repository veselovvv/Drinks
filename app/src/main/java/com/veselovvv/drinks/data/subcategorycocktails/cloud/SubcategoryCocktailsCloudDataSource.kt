package com.veselovvv.drinks.data.subcategorycocktails.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.veselovvv.drinks.core.Category

interface SubcategoryCocktailsCloudDataSource {
    suspend fun fetchCocktails(
        category: Category, subcategoryName: String
    ): List<SubcategoryCocktailCloud>

    class Base(
        private val service: SubcategoryCocktailsService, private val gson: Gson
    ) : SubcategoryCocktailsCloudDataSource {
        private val type = object : TypeToken<SubcategoryDrinksCloud>() {}.type

        override suspend fun fetchCocktails(
            category: Category, subcategoryName: String
        ): List<SubcategoryCocktailCloud> {
            val drinks = when (category) {
                Category.CATEGORIES -> service.fetchCategoriesSubcategoryCocktails(subcategoryName)
                Category.GLASS -> service.fetchGlassSubcategoryCocktails(subcategoryName)
                Category.INGREDIENTS -> service.fetchIngredientsSubcategoryCocktails(subcategoryName)
                Category.ALCOHOLIC -> service.fetchAlcoholicSubcategoryCocktails(subcategoryName)
            }

            val drinksCloud: SubcategoryDrinksCloud = gson.fromJson(drinks.string(), type)
            return drinksCloud.getCocktailsList()
        }
    }
}
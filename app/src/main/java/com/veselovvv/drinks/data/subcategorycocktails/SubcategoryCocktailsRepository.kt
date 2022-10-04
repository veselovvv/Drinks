package com.veselovvv.drinks.data.subcategorycocktails

import com.veselovvv.drinks.core.Category
import com.veselovvv.drinks.data.subcategorycocktails.cloud.SubcategoryCocktailsCloudDataSource
import com.veselovvv.drinks.data.subcategorycocktails.cloud.SubcategoryCocktailsCloudMapper

interface SubcategoryCocktailsRepository {
    suspend fun fetchCocktails(category: Category, subcategoryName: String): SubcategoryCocktailsData

    class Base(
        private val cloudDataSource: SubcategoryCocktailsCloudDataSource,
        private val cloudMapper: SubcategoryCocktailsCloudMapper
    ) : SubcategoryCocktailsRepository {
        override suspend fun fetchCocktails(category: Category, subcategoryName: String) = try {
            val cocktailsCloudList = cloudDataSource.fetchCocktails(category, subcategoryName)
            val cocktails = cloudMapper.map(cocktailsCloudList)
            SubcategoryCocktailsData.Success(cocktails)
        } catch (e: Exception) {
            SubcategoryCocktailsData.Fail(e)
        }
    }
}
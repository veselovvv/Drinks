package com.veselovvv.drinks.data.searchingredient

import com.veselovvv.drinks.data.searchingredient.cloud.IngredientCloudDataSource
import com.veselovvv.drinks.data.searchingredient.cloud.IngredientCloudMapper

interface IngredientRepository {
    suspend fun fetchIngredient(name: String): IngredientsData

    class Base(
        private val cloudDataSource: IngredientCloudDataSource,
        private val cloudMapper: IngredientCloudMapper
    ) : IngredientRepository {
        override suspend fun fetchIngredient(name: String) = try {
            val ingredientCloud = cloudDataSource.fetchIngredient(name)
            val ingredient = cloudMapper.map(ingredientCloud)
            IngredientsData.Success(ingredient)
        } catch (e: Exception) {
            IngredientsData.Fail(e)
        }
    }
}
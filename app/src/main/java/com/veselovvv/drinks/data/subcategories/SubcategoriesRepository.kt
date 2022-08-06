package com.veselovvv.drinks.data.subcategories

import com.veselovvv.drinks.data.subcategories.cloud.SubcategoriesCloudDataSource
import com.veselovvv.drinks.data.subcategories.cloud.SubcategoriesCloudMapper

interface SubcategoriesRepository {
    suspend fun fetchSubcategories(categoryKey: String): SubcategoriesData

    class Base(
        private val cloudDataSource: SubcategoriesCloudDataSource,
        private val cloudMapper: SubcategoriesCloudMapper
    ) : SubcategoriesRepository {
        override suspend fun fetchSubcategories(categoryKey: String) = try {
            val subcategoriesCloudList = when (categoryKey) {
                "c" -> cloudDataSource.fetchCategorySubcategories()
                "g" -> cloudDataSource.fetchGlassSubcategories()
                "i" -> cloudDataSource.fetchIngredientSubcategories()
                else -> cloudDataSource.fetchAlcoholicSubcategories()
            }
            val subcategories = cloudMapper.map(subcategoriesCloudList)
            SubcategoriesData.Success(subcategories)
        } catch (e: Exception) {
            SubcategoriesData.Fail(e)
        }
    }
}
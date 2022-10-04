package com.veselovvv.drinks.data.subcategories

import com.veselovvv.drinks.core.Category
import com.veselovvv.drinks.data.subcategories.cloud.SubcategoriesCloudDataSource
import com.veselovvv.drinks.data.subcategories.cloud.SubcategoriesCloudMapper

interface SubcategoriesRepository {
    suspend fun fetchSubcategories(category: Category): SubcategoriesData

    class Base(
        private val cloudDataSource: SubcategoriesCloudDataSource,
        private val cloudMapper: SubcategoriesCloudMapper
    ) : SubcategoriesRepository {
        override suspend fun fetchSubcategories(category: Category) = try {
            val subcategoriesCloudList = when (category) {
                Category.CATEGORIES -> cloudDataSource.fetchCategorySubcategories()
                Category.GLASS -> cloudDataSource.fetchGlassSubcategories()
                Category.INGREDIENTS -> cloudDataSource.fetchIngredientSubcategories()
                Category.ALCOHOLIC -> cloudDataSource.fetchAlcoholicSubcategories()
            }
            val subcategories = cloudMapper.map(subcategoriesCloudList)
            SubcategoriesData.Success(subcategories)
        } catch (e: Exception) {
            SubcategoriesData.Fail(e)
        }
    }
}
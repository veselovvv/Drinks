package com.veselovvv.drinks.data.subcategories.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface SubcategoriesCloudDataSource {
    suspend fun fetchCategorySubcategories(): List<CategorySubcategoryCloud>
    suspend fun fetchGlassSubcategories(): List<GlassSubcategoryCloud>
    suspend fun fetchIngredientSubcategories(): List<IngredientSubcategoryCloud>
    suspend fun fetchAlcoholicSubcategories(): List<AlcoholicSubcategoryCloud>

    class Base(
        private val service: SubcategoriesService, private val gson: Gson
    ) : SubcategoriesCloudDataSource {
        override suspend fun fetchCategorySubcategories(): List<CategorySubcategoryCloud> {
            val type = object : TypeToken<CategorySubcategoriesCloud>() {}.type
            val subcategoriesCloud: CategorySubcategoriesCloud =
                gson.fromJson(service.fetchCategoriesSubcategories().string(), type)
            return subcategoriesCloud.getSubcategoriesList()
        }

        override suspend fun fetchGlassSubcategories(): List<GlassSubcategoryCloud> {
            val type = object : TypeToken<GlassSubcategoriesCloud>() {}.type
            val subcategoriesCloud: GlassSubcategoriesCloud =
                gson.fromJson(service.fetchGlassSubcategories().string(), type)
            return subcategoriesCloud.getSubcategoriesList()
        }

        override suspend fun fetchIngredientSubcategories(): List<IngredientSubcategoryCloud> {
            val type = object : TypeToken<IngredientSubcategoriesCloud>() {}.type
            val subcategoriesCloud: IngredientSubcategoriesCloud =
                gson.fromJson(service.fetchIngredientsSubcategories().string(), type)
            return subcategoriesCloud.getSubcategoriesList()
        }

        override suspend fun fetchAlcoholicSubcategories(): List<AlcoholicSubcategoryCloud> {
            val type = object : TypeToken<AlcoholicSubcategoriesCloud>() {}.type
            val subcategoriesCloud: AlcoholicSubcategoriesCloud =
                gson.fromJson(service.fetchAlcoholicSubcategories().string(), type)
            return subcategoriesCloud.getSubcategoriesList()
        }
    }
}
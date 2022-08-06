package com.veselovvv.drinks.data.subcategories.cloud

import com.google.gson.annotations.SerializedName

data class CategorySubcategoriesCloud(
    @SerializedName("drinks")
    private val subcategories: List<CategorySubcategoryCloud>?
) {
    fun getSubcategoriesList() = subcategories ?: listOf()
}

data class GlassSubcategoriesCloud(
    @SerializedName("drinks")
    private val subcategories: List<GlassSubcategoryCloud>?
) {
    fun getSubcategoriesList() = subcategories ?: listOf()
}

data class IngredientSubcategoriesCloud(
    @SerializedName("drinks")
    private val subcategories: List<IngredientSubcategoryCloud>?
) {
    fun getSubcategoriesList() = subcategories ?: listOf()
}

data class AlcoholicSubcategoriesCloud(
    @SerializedName("drinks")
    private val subcategories: List<AlcoholicSubcategoryCloud>?
) {
    fun getSubcategoriesList() = subcategories ?: listOf()
}
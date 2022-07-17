package com.veselovvv.drinks.data.searchingredient.cloud

import com.google.gson.annotations.SerializedName

data class IngredientsCloud(
    @SerializedName("ingredients")
    private val ingredients: List<IngredientCloud>?
) {
    fun getIngredientsList() = ingredients ?: listOf()
}
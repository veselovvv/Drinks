package com.veselovvv.drinks.data.subcategorycocktails.cloud

import com.google.gson.annotations.SerializedName

data class SubcategoryDrinksCloud(
    @SerializedName("drinks")
    private val drinks: List<SubcategoryCocktailCloud>?
) {
    fun getCocktailsList() = drinks ?: listOf()
}

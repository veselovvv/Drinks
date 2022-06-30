package com.veselovvv.drinks.data.cocktaildetails.cloud

import com.google.gson.annotations.SerializedName

data class DrinksDetailsCloud(
    @SerializedName("drinks")
    private val drinks: List<CocktailDetailsCloud>?
) {
    fun getCocktailsList() = drinks ?: listOf()
}
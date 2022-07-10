package com.veselovvv.drinks.data.randomcocktail.cloud

import com.google.gson.annotations.SerializedName

data class DrinksRandomCloud(
    @SerializedName("drinks")
    private val drinks: List<RandomCocktailCloud>?
) {
    fun getCocktailsList() = drinks ?: listOf()
}

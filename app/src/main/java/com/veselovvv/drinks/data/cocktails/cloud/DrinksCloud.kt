package com.veselovvv.drinks.data.cocktails.cloud

import com.google.gson.annotations.SerializedName

data class DrinksCloud(
    @SerializedName("drinks")
    private val drinks: List<CocktailCloud>
) {
    fun getCocktailsList() = drinks
}
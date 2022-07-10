package com.veselovvv.drinks.data.randomcocktail.cloud

import com.google.gson.annotations.SerializedName
import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.data.randomcocktail.RandomCocktailData
import com.veselovvv.drinks.data.randomcocktail.ToRandomCocktailMapper

data class RandomCocktailCloud(
    @SerializedName("strDrink")
    private val name: String,
    @SerializedName("strCategory")
    private val category: String,
    @SerializedName("strAlcoholic")
    private val alcoholic: String,
    @SerializedName("strGlass")
    private val glass: String,
    @SerializedName("strInstructions")
    private val instructions: String,
    @SerializedName("strDrinkThumb")
    private val photoUrl: String,
    @SerializedName("strIngredient1")
    private val ingredient1: String,
    @SerializedName("strIngredient2")
    private val ingredient2: String,
    @SerializedName("strIngredient3")
    private val ingredient3: String?,
    @SerializedName("strIngredient4")
    private val ingredient4: String?,
    @SerializedName("strIngredient5")
    private val ingredient5: String?,
    @SerializedName("strIngredient6")
    private val ingredient6: String?,
    @SerializedName("strIngredient7")
    private val ingredient7: String?,
    @SerializedName("strIngredient8")
    private val ingredient8: String?,
    @SerializedName("strIngredient9")
    private val ingredient9: String?,
    @SerializedName("strIngredient10")
    private val ingredient10: String?
) : Object<RandomCocktailData, ToRandomCocktailMapper> {
    override fun map(mapper: ToRandomCocktailMapper) = mapper.map(
        name, category, alcoholic, glass, instructions, photoUrl, listOf(
            ingredient1, ingredient2, ingredient3 ?: "", ingredient4 ?: "", ingredient5 ?: "",
            ingredient6 ?: "", ingredient7 ?: "", ingredient8 ?: "", ingredient9 ?: "", ingredient10 ?: ""
        )
    )
}

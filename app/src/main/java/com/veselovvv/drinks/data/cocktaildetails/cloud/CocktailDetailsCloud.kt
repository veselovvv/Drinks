package com.veselovvv.drinks.data.cocktaildetails.cloud

import com.google.gson.annotations.SerializedName
import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsData
import com.veselovvv.drinks.data.cocktaildetails.ToCocktailDetailsMapper

data class CocktailDetailsCloud(
    @SerializedName("strDrink")
    private val name: String,
    @SerializedName("strAlcoholic")
    private val alcoholic: String,
    @SerializedName("strGlass")
    private val glass: String,
    @SerializedName("strInstructions")
    private val instructions: String,
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
) : Object<CocktailDetailsData, ToCocktailDetailsMapper> {
    fun getName() = name

    override fun map(mapper: ToCocktailDetailsMapper) = mapper.map(
        name, alcoholic, glass, instructions, listOf(
            ingredient1, ingredient2, ingredient3 ?: "", ingredient4 ?: "", ingredient5 ?: "",
            ingredient6 ?: "", ingredient7 ?: "", ingredient8 ?: "", ingredient9 ?: "", ingredient10 ?: ""
        )
    )
}

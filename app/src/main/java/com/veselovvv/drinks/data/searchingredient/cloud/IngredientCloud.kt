package com.veselovvv.drinks.data.searchingredient.cloud

import com.google.gson.annotations.SerializedName
import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.data.searchingredient.IngredientData
import com.veselovvv.drinks.data.searchingredient.ToIngredientMapper

data class IngredientCloud(
    @SerializedName("strIngredient")
    private val name: String,
    @SerializedName("strDescription")
    private val description: String?,
    @SerializedName("strType")
    private val type: String?,
    @SerializedName("strAlcohol")
    private val alcohol: String?,
    @SerializedName("strABV")
    private val abv: String?
) : Object<IngredientData, ToIngredientMapper> {
    override fun map(mapper: ToIngredientMapper) =
        mapper.map(name, description ?: "", type ?: "", alcohol ?: "", abv ?: "")
}
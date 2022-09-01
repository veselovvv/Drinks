package com.veselovvv.drinks.data.subcategorycocktails.cloud

import com.google.gson.annotations.SerializedName
import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.data.subcategorycocktails.SubcategoryCocktailData
import com.veselovvv.drinks.data.subcategorycocktails.ToSubcategoryCocktailMapper

data class SubcategoryCocktailCloud(
    @SerializedName("idDrink")
    private val id: String,
    @SerializedName("strDrink")
    private val name: String,
    @SerializedName("strDrinkThumb")
    private val photoUrl: String
) : Object<SubcategoryCocktailData, ToSubcategoryCocktailMapper> {
    override fun map(mapper: ToSubcategoryCocktailMapper) = mapper.map(id, name, photoUrl)
}

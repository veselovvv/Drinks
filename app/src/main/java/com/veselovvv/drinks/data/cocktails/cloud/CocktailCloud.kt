package com.veselovvv.drinks.data.cocktails.cloud

import com.google.gson.annotations.SerializedName
import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.data.cocktails.CocktailData
import com.veselovvv.drinks.data.cocktails.ToCocktailMapper

data class CocktailCloud(
    @SerializedName("idDrink")
    private val id: String,
    @SerializedName("strDrink")
    private val name: String,
    @SerializedName("strCategory")
    private val category: String,
    @SerializedName("strDrinkThumb")
    private val photoUrl: String
) : Object<CocktailData, ToCocktailMapper> {
    override fun map(mapper: ToCocktailMapper) = mapper.map(id, name, category, photoUrl)
}

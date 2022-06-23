package com.veselovvv.drinks.data.cocktails.cloud

import com.veselovvv.drinks.data.cocktails.CocktailData
import com.veselovvv.drinks.data.cocktails.ToCocktailMapper

interface CocktailsCloudMapper {
    fun map(cocktailsCloudList: List<CocktailCloud>): List<CocktailData>

    class Base(private val toCocktailMapper: ToCocktailMapper) : CocktailsCloudMapper {
        override fun map(cocktailsCloudList: List<CocktailCloud>) = cocktailsCloudList.map { cocktailCloud ->
            cocktailCloud.map(toCocktailMapper)
        }
    }
}
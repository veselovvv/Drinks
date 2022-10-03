package com.veselovvv.drinks.data.cocktails.cloud

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.data.cocktails.CocktailData
import com.veselovvv.drinks.data.cocktails.ToCocktailMapper

interface CocktailsCloudMapper : Mapper.Data<List<CocktailCloud>, List<CocktailData>> {
    class Base(private val toCocktailMapper: ToCocktailMapper) : CocktailsCloudMapper {
        override fun map(data: List<CocktailCloud>) = data.map { cocktailCloud ->
            cocktailCloud.map(toCocktailMapper)
        }
    }
}
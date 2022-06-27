package com.veselovvv.drinks.data.cocktaildetails.cloud

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsData
import com.veselovvv.drinks.data.cocktaildetails.ToCocktailDetailsMapper

interface CocktailDetailsCloudMapper : Mapper.Data<CocktailDetailsCloud, CocktailDetailsData> {
    override fun map(data: CocktailDetailsCloud): CocktailDetailsData

    class Base(private val toCocktailDetailsMapper: ToCocktailDetailsMapper) : CocktailDetailsCloudMapper {
        override fun map(data: CocktailDetailsCloud) = data.map(toCocktailDetailsMapper)
    }
}
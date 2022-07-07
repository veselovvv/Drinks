package com.veselovvv.drinks.data.cocktaildetails.cache

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsData
import com.veselovvv.drinks.data.cocktaildetails.ToCocktailDetailsMapper

interface CocktailDetailsCacheMapper : Mapper.Data<CocktailDetailsDb, CocktailDetailsData> {
    class Base(private val mapper: ToCocktailDetailsMapper) : CocktailDetailsCacheMapper {
        override fun map(data: CocktailDetailsDb) = data.map(mapper)
    }
}
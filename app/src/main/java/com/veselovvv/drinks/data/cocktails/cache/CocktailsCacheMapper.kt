package com.veselovvv.drinks.data.cocktails.cache

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.data.cocktails.CocktailData
import com.veselovvv.drinks.data.cocktails.ToCocktailMapper

interface CocktailsCacheMapper : Mapper.Data<List<CocktailDb>, List<CocktailData>> {
    class Base(private val mapper: ToCocktailMapper) : CocktailsCacheMapper {
        override fun map(data: List<CocktailDb>) = data.map { it.map(mapper)  }
    }
}
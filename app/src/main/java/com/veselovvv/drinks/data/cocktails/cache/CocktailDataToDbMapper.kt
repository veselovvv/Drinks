package com.veselovvv.drinks.data.cocktails.cache

import com.veselovvv.drinks.core.Mapper

interface CocktailDataToDbMapper : Mapper {
    fun mapToDb(id: String, name: String, category: String, photoUrl: String): CocktailDb

    class Base : CocktailDataToDbMapper {
        override fun mapToDb(id: String, name: String, category: String, photoUrl: String) =
            CocktailDb(id, name, category, photoUrl)
    }
}
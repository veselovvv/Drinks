package com.veselovvv.drinks.data.cocktails

import com.veselovvv.drinks.core.Mapper

interface ToCocktailMapper : Mapper {
    fun map(id: String, name: String, category: String, photoUrl: String): CocktailData

    class Base : ToCocktailMapper {
        override fun map(id: String, name: String, category: String, photoUrl: String) =
            CocktailData(id, name, category, photoUrl)
    }
}
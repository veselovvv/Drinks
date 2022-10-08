package com.veselovvv.drinks.data.subcategorycocktails

import com.veselovvv.drinks.core.Mapper

interface ToSubcategoryCocktailMapper : Mapper {
    fun map(name: String, photoUrl: String): SubcategoryCocktailData

    class Base : ToSubcategoryCocktailMapper {
        override fun map(name: String, photoUrl: String) =
            SubcategoryCocktailData(name, photoUrl)
    }
}
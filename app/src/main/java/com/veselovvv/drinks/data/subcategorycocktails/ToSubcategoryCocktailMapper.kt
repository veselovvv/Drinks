package com.veselovvv.drinks.data.subcategorycocktails

import com.veselovvv.drinks.core.Mapper

interface ToSubcategoryCocktailMapper : Mapper {
    fun map(id: String, name: String, photoUrl: String): SubcategoryCocktailData

    class Base : ToSubcategoryCocktailMapper {
        override fun map(id: String, name: String, photoUrl: String) =
            SubcategoryCocktailData(id, name, photoUrl)
    }
}
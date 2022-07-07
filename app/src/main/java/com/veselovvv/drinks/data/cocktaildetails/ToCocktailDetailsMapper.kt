package com.veselovvv.drinks.data.cocktaildetails

import com.veselovvv.drinks.core.Mapper

interface ToCocktailDetailsMapper : Mapper {
    fun map(
        name: String, alcoholic: String, glass: String, instructions: String, ingredients: List<String>
    ): CocktailDetailsData

    class Base : ToCocktailDetailsMapper {
        override fun map(
            name: String, alcoholic: String, glass: String, instructions: String, ingredients: List<String>
        ) = CocktailDetailsData(name, alcoholic, glass, instructions, ingredients)
    }
}
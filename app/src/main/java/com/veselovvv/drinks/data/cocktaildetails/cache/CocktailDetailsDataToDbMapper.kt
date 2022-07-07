package com.veselovvv.drinks.data.cocktaildetails.cache

import com.veselovvv.drinks.core.Mapper

interface CocktailDetailsDataToDbMapper : Mapper {
    fun mapToDb(
        name: String, alcoholic: String, glass: String, instructions: String, ingredients: List<String>
    ): CocktailDetailsDb

    class Base : CocktailDetailsDataToDbMapper {
        override fun mapToDb(
            name: String, alcoholic: String, glass: String, instructions: String, ingredients: List<String>
        ) = CocktailDetailsDb(
            name, alcoholic, glass, instructions,
            ingredients[0], ingredients[1], ingredients[2], ingredients[3], ingredients[4],
            ingredients[5], ingredients[6], ingredients[7], ingredients[8], ingredients[9]
        )
    }
}
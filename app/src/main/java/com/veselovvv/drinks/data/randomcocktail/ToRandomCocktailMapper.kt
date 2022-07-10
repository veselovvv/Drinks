package com.veselovvv.drinks.data.randomcocktail

import com.veselovvv.drinks.core.Mapper

interface ToRandomCocktailMapper : Mapper {
    fun map(
        name: String, category: String, alcoholic: String, glass: String,
        instructions: String, photoUrl: String, ingredients: List<String>
    ): RandomCocktailData

    class Base : ToRandomCocktailMapper {
        override fun map(
            name: String, category: String, alcoholic: String, glass: String,
            instructions: String, photoUrl: String, ingredients: List<String>
        ) = RandomCocktailData(name, category, alcoholic, glass, instructions, photoUrl, ingredients)
    }
}
package com.veselovvv.drinks.domain.randomcocktail

import com.veselovvv.drinks.data.randomcocktail.RandomCocktailDataToDomainMapper

class BaseRandomCocktailDataToDomainMapper : RandomCocktailDataToDomainMapper {
    override fun map(
        name: String,
        category: String,
        alcoholic: String,
        glass: String,
        instructions: String,
        photoUrl: String,
        ingredients: List<String>
    ) = RandomCocktailDomain(name, category, alcoholic, glass, instructions, photoUrl, ingredients)
}
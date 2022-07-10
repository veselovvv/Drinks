package com.veselovvv.drinks.presentation.randomcocktail

import com.veselovvv.drinks.domain.randomcocktail.RandomCocktailDomainToUiMapper

class BaseRandomCocktailDomainToUiMapper : RandomCocktailDomainToUiMapper {
    override fun map(
        name: String,
        category: String,
        alcoholic: String,
        glass: String,
        instructions: String,
        photoUrl: String,
        ingredients: List<String>
    ) = RandomCocktailUi.Base(name, category, alcoholic, glass, instructions, photoUrl, ingredients)
}
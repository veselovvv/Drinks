package com.veselovvv.drinks.domain.randomcocktail

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.presentation.randomcocktail.RandomCocktailUi

interface RandomCocktailDomainToUiMapper : Mapper {
    fun map(
        name: String,
        category: String,
        alcoholic: String,
        glass: String,
        instructions: String,
        photoUrl: String,
        ingredients: List<String>
    ): RandomCocktailUi
}
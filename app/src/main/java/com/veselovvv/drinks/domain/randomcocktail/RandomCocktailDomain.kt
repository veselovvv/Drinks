package com.veselovvv.drinks.domain.randomcocktail

import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.presentation.randomcocktail.RandomCocktailUi

data class RandomCocktailDomain(
    private val name: String,
    private val category: String,
    private val alcoholic: String,
    private val glass: String,
    private val instructions: String,
    private val photoUrl: String,
    private val ingredients: List<String>
) : Object<RandomCocktailUi, RandomCocktailDomainToUiMapper> {
    override fun map(mapper: RandomCocktailDomainToUiMapper) =
        mapper.map(name, category, alcoholic, glass, instructions, photoUrl, ingredients)
}